package pe.lucky.xplora.model;

public class Tienda {

    private int tiendaId;
    private String nombre;
    private String direccion;
    private String latitud;
    private String longitud;


    public Tienda(int tiendaId, String nombre, String direccion, String latitud, String longitud) {
        this.tiendaId = tiendaId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getTiendaId() {
        return tiendaId;
    }

    public void setTiendaId(int tiendaId) {
        this.tiendaId = tiendaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Tienda{" +
                "tiendaId=" + tiendaId +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                '}';
    }
}
