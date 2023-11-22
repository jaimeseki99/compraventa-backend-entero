package net.ausiasmarch.compraventa.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.ausiasmarch.compraventa.entity.CompraEntity;
import net.ausiasmarch.compraventa.entity.ProductoEntity;
import net.ausiasmarch.compraventa.entity.UsuarioEntity;
import net.ausiasmarch.compraventa.exception.InssuficientSaldoException;
import net.ausiasmarch.compraventa.exception.InssuficientStockException;
import net.ausiasmarch.compraventa.exception.ResourceNotFoundException;
import net.ausiasmarch.compraventa.helper.DataGenerationHelper;
import net.ausiasmarch.compraventa.repository.CompraRepository;
import net.ausiasmarch.compraventa.repository.ProductoRepository;
import net.ausiasmarch.compraventa.repository.UsuarioRepository;

@Service
public class CompraService {

    @Autowired
    CompraRepository oCompraRepository;

    @Autowired
    ProductoService oProductoService;

    @Autowired
    UsuarioService oUsuarioService;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    ProductoRepository oProductoRepository;

    @Autowired
    SesionService oSesionService;

    public CompraEntity get(Long id) {
        return oCompraRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Compra no encontrada"));
    }

    public Long create(CompraEntity oCompraEntity) {

       // oSesionService.onlyAdminsOrUsersWithIisOwnData(oCompraEntity.getUsuario().getId());
        oSesionService.onlyAdminsOrUsers();
        
        oCompraEntity.setId(null);
        UsuarioEntity usuarioCompra;
        ProductoEntity productoComprado = oProductoService.get(oCompraEntity.getProducto().getId());
        if (oSesionService.isAdmin()) {
          //  usuarioCompra = oSesionService.getSesionUsuario();
            usuarioCompra = oUsuarioService.get(oCompraEntity.getUsuario().getId());
        } else {
            usuarioCompra = oSesionService.getSesionUsuario();
        }
        int cantidadComprada = oCompraEntity.getCantidad();
        if (cantidadComprada > productoComprado.getStock()) {
            cantidadComprada = productoComprado.getStock();
        }
        double precio = productoComprado.getPrecio();
        if ((usuarioCompra.getSaldo() < (precio * cantidadComprada)) || (usuarioCompra.getSaldo() == 0)) {
            throw new InssuficientSaldoException("No hay suficiente saldo para realizar la compra");
        } else if (productoComprado.getStock() == 0) {
            throw new InssuficientStockException("El producto está agotado");
        } else {
            oUsuarioService.actualizarSaldoUsuario(usuarioCompra, cantidadComprada * precio);
            oProductoService.actualizarStock(productoComprado, cantidadComprada);

            CompraEntity oCompraEntityCreada = new CompraEntity(cantidadComprada, cantidadComprada * precio,
                    LocalDateTime.now(), usuarioCompra, productoComprado);

            return oCompraRepository.save(oCompraEntityCreada).getId();
        }
    }

    public CompraEntity update(CompraEntity oCompraEntity) {

        CompraEntity oCompraEntityBaseDatos = this.get(oCompraEntity.getId());
        oSesionService.onlyAdminsOrUsersWithIisOwnData(oCompraEntityBaseDatos.getUsuario().getId());

        ProductoEntity productoComprado = oProductoService.get(oCompraEntity.getProducto().getId());
        UsuarioEntity usuario;
        if (oSesionService.isUser()) {
            usuario = oSesionService.getSesionUsuario();
        } else {
            usuario = oUsuarioService.get(oCompraEntity.getUsuario().getId());
        }
        oCompraEntity.setUsuario(usuario);

        int cantidadActualizada = oCompraEntity.getCantidad();
        int cantidadOriginal = oCompraEntityBaseDatos.getCantidad();
        int cantidadDiferencia = cantidadActualizada - cantidadOriginal;

        double costeTotal = cantidadActualizada * productoComprado.getPrecio();
        oCompraEntity.setCoste(costeTotal);
        double costeDiferencia = Math.abs(cantidadDiferencia) * productoComprado.getPrecio();

        if (cantidadDiferencia != 0) {
            oProductoService.actualizarStock(productoComprado, cantidadDiferencia);
            if (cantidadDiferencia > 0) {
                oUsuarioService.actualizarSaldoUsuario(usuario, costeDiferencia);
            } else {
                oUsuarioService.actualizarSaldoUsuario(usuario, -costeDiferencia);
            }
        }

        return oCompraRepository.save(oCompraEntity);
    }

    public Long delete(Long id) {

        CompraEntity oCompraCancelada = this.get(id);
        oSesionService.onlyAdminsOrUsersWithIisOwnData(oCompraCancelada.getUsuario().getId());

        if (oCompraCancelada != null) {
            int cantidadVendida = oCompraCancelada.getCantidad();
            double costeCompra = oCompraCancelada.getCoste();
            ProductoEntity productoVendido = oCompraCancelada.getProducto();
            UsuarioEntity usuarioCompra = oCompraCancelada.getUsuario();

            if (productoVendido != null) {
                oProductoService.actualizarStock(productoVendido, -cantidadVendida);
            }

            oUsuarioService.actualizarSaldoUsuario(usuarioCompra, -costeCompra);

            oCompraRepository.deleteById(id);
        }

        return id;
    }

    public Page<CompraEntity> getPage(Pageable oPageable, Long id_usuario, Long id_producto) {
        oSesionService.onlyAdminsOrUsers();
        if (id_usuario == 0) {
            if (id_producto == 0) {
                return oCompraRepository.findAll(oPageable);
            } else {
                return oCompraRepository.findByProductoId(id_producto, oPageable);
            }
        } else {
            if (id_producto == 0) {
                return oCompraRepository.findByUsuarioId(id_usuario, oPageable);
            } else {
                return oCompraRepository.findByUsuarioIdAndProductoId(id_usuario, id_producto, oPageable);
            }
        }
    }

    public Long populate(Integer amount) {
        oSesionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            int cantidad = DataGenerationHelper.generarIntRandom();
            double coste = DataGenerationHelper.generarDobleRandom();
            oCompraRepository.save(new CompraEntity(cantidad, coste, DataGenerationHelper.getFechaRandom(),
                    oUsuarioService.getOneRandom(), oProductoService.getOneRandom()));
        }
        return oCompraRepository.count();
    }

    @Transactional
    public Long empty() {
        oSesionService.onlyAdmins();
        oCompraRepository.deleteAll();
        oCompraRepository.resetAutoIncrement();
        oCompraRepository.flush();
        return oCompraRepository.count();
    }

}
