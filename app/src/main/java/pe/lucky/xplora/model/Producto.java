package pe.lucky.xplora.model;

public class Producto {

    private int productoId;
    private String sku;
    private double precioCosto;
    private double precioRvta;
    private int stock;
    private int tiendaId;

    public Producto() {
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public double getPrecioRvta() {
        return precioRvta;
    }

    public void setPrecioRvta(double precioRvta) {
        this.precioRvta = precioRvta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getTiendaId() {
        return tiendaId;
    }

    public void setTiendaId(int tiendaId) {
        this.tiendaId = tiendaId;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "productoId=" + productoId +
                ", sku='" + sku + '\'' +
                ", precioCosto=" + precioCosto +
                ", precioRvta=" + precioRvta +
                ", stock=" + stock +
                ", tiendaId=" + tiendaId +
                '}';
    }
}
