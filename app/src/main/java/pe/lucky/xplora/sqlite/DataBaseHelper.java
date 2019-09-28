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


    public static final int DATABASE_VERSION = 20;
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

        db.execSQL("INSERT INTO " + Constantes.TABLE_USER + " (username, password) VALUES ('admin', 'admin')");

        db.execSQL("INSERT INTO " + Constantes.TABLE_TIENDA + " (nombre,direccion) VALUES ('Bodega Paquita', 'Av. Perú # 1350- San Martin de Porres')");
        db.execSQL("INSERT INTO " + Constantes.TABLE_TIENDA + " (nombre,direccion) VALUES ('Metro Yzaguirre', 'Av. Perú # 2660- San Martin de Porres')");

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

    public Usuario queryUser(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Usuario usuario = null;

        Cursor cursor = db.query(Constantes.TABLE_USER, new String[]{Constantes.COLUMN_USER_ID,
                        Constantes.COLUMN_USER_NAME, Constantes.COLUMN_USER_PASSWORD}, Constantes.COLUMN_USER_NAME + "=? and " + Constantes.COLUMN_USER_PASSWORD + "=?",
                new String[]{username, password}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            usuario = new Usuario(cursor.getString(1), cursor.getString(2));
        }
        // return user
        return usuario;
    }

    public void addUser(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constantes.COLUMN_USER_NAME, usuario.getUsername());
        values.put(Constantes.COLUMN_USER_PASSWORD, usuario.getPassword());

        // Inserting Row
        db.insert(Constantes.TABLE_USER, null, values);
        db.close(); // Closing database connection

    }

    public void addTienda(Tienda tienda) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constantes.COLUMN_TIENDA_NOMBRE, tienda.getNombre());
        values.put(Constantes.COLUMN_TIENDA_DIRECCION, tienda.getDireccion());

        // Inserting Row
        db.insert(Constantes.TABLE_TIENDA, null, values);
        db.close(); // Closing database connection

    }


}
