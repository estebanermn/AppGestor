package pe.lucky.xplora.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import pe.lucky.xplora.util.Constantes;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 26;
    // Database Version

    // Database Name
    public static final String DATABASE_NAME = "xplore.db";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constantes.CREATE_USER_TABLE);
        db.execSQL(Constantes.CREATE_TIENDA_TABLE);
        db.execSQL(Constantes.CREATE_PRODUCTO_TABLE);

        db.execSQL("INSERT INTO " + Constantes.TABLE_USER + " (username, password, email, nombre) VALUES ('admin', 'admin' , 'antonioherrerau@gmail.com','Antonio Herrera Ubillus')");
        db.execSQL("INSERT INTO " + Constantes.TABLE_USER + " (username, password, email, nombre) VALUES ('admin2', 'admin2' , 'estebanermn@gmail.com','Esteban Medina Napurí')");


        db.execSQL("INSERT INTO " + Constantes.TABLE_TIENDA + " (nombre,direccion, latitud,longitud) VALUES ('Bodega Paquita', 'Av. Perú # 1350- San Martin de Porres', -12.0321695,-77.0608359)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_TIENDA + " (nombre,direccion,latitud,longitud) VALUES ('Metro Yzaguirre', 'Av. Perú # 2660- San Martin de Porres', -12.0316481,-77.0771404)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_TIENDA + " (nombre,direccion,latitud,longitud) VALUES ('Ernesto Tito Gálvez/ Mrcd. Las Frutas', 'Av. Perú # 1360- San Martin de Porres', -12.0326679,-77.0617391)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_TIENDA + " (nombre,direccion,latitud,longitud) VALUES ('Ferreteriá Carsina SAC/ Mrcd. Las Frutas', 'Av. Perú # 1741- San Martin de Porres', -12.0321723,-77.0644843)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_TIENDA + " (nombre,direccion,latitud,longitud) VALUES ('Ferreteriá Carsina SAC/ Mrcd. Las Frutas', 'Av. Perú # 1741- San Martin de Porres', -12.0321723,-77.0644843)");




        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Cilx12 Bot', 45.23, 45.23, 100, 1)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Primorx12 Bot', 42.00, 45.23, 120, 1)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Saox12 Bot', 41.50, 45.23, 30, 1)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Idealx12 Bot', 47.30, 45.23, 20, 1)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Metrox12 Bot', 41.10, 45.23, 10, 1)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Bellsx12 Bot', 38.23, 45.23, 17, 1)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Wongx12 Bot', 45.23, 45.23, 19, 1)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Cocinerox12 Bot', 41.50, 45.23, 32, 1)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Crisolx12 Bot', 41.50, 45.23, 33, 1)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Bells12 Bot', 43.00, 45.23, 23, 1)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite SolSurox12 Bot', 44.50, 45.23, 56, 1)");


        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Cocinerox12 Bot', 41.50, 45.23, 32, 2)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Crisolx12 Bot', 41.50, 45.23, 33, 2)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Bells12 Bot', 43.00, 45.23, 23, 3)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite SolSurox12 Bot', 44.50, 45.23, 56, 3)");

        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Metrox12 Bot', 41.10, 45.23, 10, 4)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Bellsx12 Bot', 38.23, 45.23, 17, 4)");
        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Wongx12 Bot', 45.23, 45.23, 19, 5)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL(Constantes.DROP_USER_TABLE);
        db.execSQL(Constantes.DROP_TIENDA_TABLE);
        db.execSQL(Constantes.DROP_PRODUCTO_TABLE);

        // Create tables again
        onCreate(db);
    }


}
