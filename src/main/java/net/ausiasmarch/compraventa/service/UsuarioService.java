package net.ausiasmarch.compraventa.service;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.DeferredResultMethodReturnValueHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import net.ausiasmarch.compraventa.entity.UsuarioEntity;
import net.ausiasmarch.compraventa.exception.ResourceNotFoundException;
import net.ausiasmarch.compraventa.helper.DataGenerationHelper;
import net.ausiasmarch.compraventa.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SesionService oSesionService;

    public UsuarioEntity get(Long id){
        return oUsuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    public UsuarioEntity getByUsername(String username){
        return oUsuarioRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

   public Long create (UsuarioEntity oUsuarioEntity) {
      // oSesionService.onlyAdmins();
       oUsuarioEntity.setId(null);
       oUsuarioEntity.setContrasenya("88dc10643ea6bde90909b45b01fcfd1bf6d96a0298d2b0a86f65b55192cae99b");
       return oUsuarioRepository.save(oUsuarioEntity).getId();
    }

    public UsuarioEntity update(UsuarioEntity oUsuarioEntity) {
        UsuarioEntity oUsuarioEntityBaseDatos = this.get(oUsuarioEntity.getId());
        // oSesionService.onlyAdminsOrUsersWithIisOwnData(oUsuarioEntityBaseDatos.getId());
            //oUsuarioEntity.setId(null);
            oUsuarioEntity.setRol(oUsuarioEntityBaseDatos.getRol());
            oUsuarioEntity.setContrasenya("88dc10643ea6bde90909b45b01fcfd1bf6d96a0298d2b0a86f65b55192cae99b");
            return oUsuarioRepository.save(oUsuarioEntity);
    }

    public Long delete(Long id) {
        // oSesionService.onlyAdmins();
        oUsuarioRepository.deleteById(id);
        return id;
    }

    public Page<UsuarioEntity> getPage(Pageable oPageable) {
       // oSesionService.onlyAdminsOrUsers();
        return oUsuarioRepository.findAll(oPageable);
    }

    public Long populate(Integer amount) {
        //oSesionService.onlyAdmins();
        for (int i=0; i < amount; i++) {
            String nombre = DataGenerationHelper.getNombreRandom();
            String apellido = DataGenerationHelper.getApellidoRandom();
            String username = DataGenerationHelper.doNormalizeString(nombre.substring(0,3).toLowerCase() + apellido.substring(0,2).toLowerCase());
            String email = DataGenerationHelper.doNormalizeString(nombre.toLowerCase() + apellido.toLowerCase()); 
            String direccion = DataGenerationHelper.generarDireccionRandom();
            String telefono = DataGenerationHelper.generarNumeroTelefono();
            double saldo = DataGenerationHelper.generarDobleRandom();
          oUsuarioRepository.save(new UsuarioEntity(nombre, apellido, username, email + "@gmail.com", direccion, telefono, saldo ,"88dc10643ea6bde90909b45b01fcfd1bf6d96a0298d2b0a86f65b55192cae99b", true));
        }
        return oUsuarioRepository.count();
    }

    public UsuarioEntity getOneRandom() {
       // oSesionService.onlyAdmins();
        Pageable oPageable = PageRequest.of((int) (Math.random() * oUsuarioRepository.count()), 1);
        return oUsuarioRepository.findAll(oPageable).getContent().get(0);
    }

    @Transactional
    public Long empty() {
        // oSesionService.onlyAdmins();
        oUsuarioRepository.deleteAll();
        oUsuarioRepository.resetAutoIncrement();
        UsuarioEntity oUsuarioEntity1 = new UsuarioEntity(1L, "Jaime", "Serrano", "jaimeseki99", "jaime99sq@gmail.com", "C/La Senyera, 24", "601148404", 1000000.00, "88dc10643ea6bde90909b45b01fcfd1bf6d96a0298d2b0a86f65b55192cae99b", false);
        oUsuarioRepository.save(oUsuarioEntity1);
        oUsuarioEntity1 = new UsuarioEntity(2L, "Joan", "Serrano", "joaseki", "joaserqu93@hotmail.com", "C/ La Plata, 54", "693797773", 1000000.00, "88dc10643ea6bde90909b45b01fcfd1bf6d96a0298d2b0a86f65b55192cae99b",true);
        oUsuarioRepository.save(oUsuarioEntity1);
        return oUsuarioRepository.count();
    }

    @Transactional
    public void actualizarSaldoUsuario(UsuarioEntity oUsuarioEntity, double costeTotal) {
        UsuarioEntity usuarioEncontrado = oUsuarioRepository.findById(oUsuarioEntity.getId()).orElse(null);

                if (usuarioEncontrado != null) {
                    double saldoActual = usuarioEncontrado.getSaldo();
                    double nuevoSaldo = saldoActual - costeTotal;

                    if (nuevoSaldo >= 0) {
                        usuarioEncontrado.setSaldo(nuevoSaldo);
                        oUsuarioRepository.save(usuarioEncontrado);
                    }
                } else {
                    throw new ResourceNotFoundException("Usuario no encontrado");
                }
            }
}
