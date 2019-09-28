package pe.lucky.xplora.model;

public class Tienda {

    private int tiendaId;
    private String nombre;
    private String direccion;
    private double latitud;
    private double longitud;

    public Tienda(int tiendaId, String nombre, String direccion, double latitud, double longitud) {
        this.tiendaId = tiendaId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Tienda() {
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

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
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
