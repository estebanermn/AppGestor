package pe.lucky.xplora.util;

public class Constantes {

    // User table name
    public static final String TABLE_USER = "usuarios";

    // User Table Columns names
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";

    // create table sql query
    public static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " VARCHAR(45),"
            + COLUMN_USER_PASSWORD + " VARCHAR(45))";

    // drop table sql query
    public static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;


    // Tienda table name
    public static final String
            TABLE_TIENDA = "tiendas";

    // Tienda Table Columns names
    public static final String COLUMN_TIENDA_ID = "tiendaId";
    public static final String COLUMN_TIENDA_NOMBRE = "nombre";
    public static final String COLUMN_TIENDA_DIRECCION = "direccion";
    public static final String COLUMN_TIENDA_LATITUD = "latitud";
    public static final String COLUMN_TIENDA_LONGITUD = "longitud";


    // create table sql query
    public static final String CREATE_TIENDA_TABLE = "CREATE TABLE " + TABLE_TIENDA + "("
            + COLUMN_TIENDA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TIENDA_NOMBRE + " VARCHAR(70),"
            + COLUMN_TIENDA_DIRECCION + " VARCHAR(200),"+ COLUMN_TIENDA_LATITUD + " VARCHAR(45)," + COLUMN_TIENDA_LONGITUD+ " VARCHAR(45))";


    // drop table sql query
    public static final String DROP_TIENDA_TABLE = "DROP TABLE IF EXISTS " + TABLE_TIENDA;


    // Producto table name
    public static final String
            TABLE_PRODUCTO = "productos";

    // ¨PRoducto Table Columns names
    public static final String COLUMN_PRODUCTO_ID = "productoId";
    public static final String COLUMN_PRODUCTO_SKU = "sku";
    public static final String COLUMN_PRODUCTO_PRECIO_COSTO = "precioCosto";
    public static final String COLUMN_PRODUCTO_PRECIO_RVTA = "precioRvta";
    public static final String COLUMN_PRODUCTO_STOCK = "stock";
    public static final String COLUMN_PRODUCTO_TIENDA_ID = "tiendaId";


    // create table sql query
    public static final String CREATE_PRODUCTO_TABLE = "CREATE TABLE " + TABLE_PRODUCTO + "( " + COLUMN_PRODUCTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PRODUCTO_SKU + " VARCHAR(70), " + COLUMN_PRODUCTO_PRECIO_COSTO + " REAL," + COLUMN_PRODUCTO_PRECIO_RVTA + " REAL,"  + COLUMN_PRODUCTO_STOCK +	" INTEGER,"
            + COLUMN_PRODUCTO_TIENDA_ID + "	INTEGER, FOREIGN KEY(tiendaId) REFERENCES tiendas(tiendaId) ON DELETE SET NULL)";

    // drop table sql query
    public static final String DROP_PRODUCTO_TABLE = "DROP TABLE IF EXISTS " + TABLE_PRODUCTO;




}
