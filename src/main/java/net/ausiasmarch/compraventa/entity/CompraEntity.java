package net.ausiasmarch.compraventa.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "compra")
public class CompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 1, message = "Debes comprar al menos una unidad para efectuar la compra")
    private int cantidad;

    private double coste;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime fecha;

    @NotNull(message = "El usuario que realiza la compra no puede estar vacío")
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @NotNull(message = "El producto comprado no puede estar vacío")
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public CompraEntity () {
        
    }

    public CompraEntity(int cantidad, double coste) {
        this.cantidad = cantidad;
        this.coste = coste;
        this.fecha = LocalDateTime.now();
    }

    public CompraEntity(Long id, int cantidad, double coste) {
        this.id = id;
        this.cantidad = cantidad;
        this.coste = coste;
        this.fecha = LocalDateTime.now();
    }

    public CompraEntity(int cantidad, double coste, LocalDateTime fecha, UsuarioEntity usuario, ProductoEntity producto) {
        this.cantidad = cantidad;
        this.coste = coste;
        this.fecha = fecha;
        this.usuario = usuario;
        this.producto = producto;
    }

    
}
