package pe.lucky.xplora.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pe.lucky.xplora.model.Tienda;
import pe.lucky.xplora.model.Usuario;
import pe.lucky.xplora.util.Constantes;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 25;
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

        db.execSQL("INSERT INTO " + Constantes.TABLE_PRODUCTO + " (sku, precioCosto, precioRvta, stock, tiendaId) VALUES ('Aceite Cilx12 Bot', 45.23, 45.23, 100, 1)");

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
