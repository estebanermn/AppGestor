package pe.lucky.xplora.model;

public class Usuario {

    private int usuarioId;
    private String username;
    private String password;

    public Usuario(int usuarioId, String username, String password) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.password = password;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "Usuario{" +
                "usuarioId=" + usuarioId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
