package pe.lucky.xplora.model;

import java.io.Serializable;

public class Usuario  implements Serializable {

//public class Usuario {
    private int usuarioId;
    private String username;
    private String password;
    private String email;
    private String nombre;

    public Usuario() {
    }


    public Usuario(int usuarioId, String username, String password, String email, String nombre) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nombre = nombre;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuarioId=" + usuarioId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
