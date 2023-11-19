package net.ausiasmarch.compraventa.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioBean {

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasenya;

    private boolean rol;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }    

    public boolean isRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }

}
