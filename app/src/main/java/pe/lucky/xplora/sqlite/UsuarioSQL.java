package pe.lucky.xplora.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import pe.lucky.xplora.model.Usuario;
import pe.lucky.xplora.util.Constantes;

public class UsuarioSQL {

    private DataBaseHelper conexion;

    public UsuarioSQL(Context context) {
        conexion = new DataBaseHelper(context);
    }

    public Usuario queryUser(String username, String password) {

        SQLiteDatabase db = conexion.getReadableDatabase();
        Usuario usuario = null;

        try {

            Cursor cursor = null;

            String query = "select * from " + Constantes.TABLE_USER + " where " + Constantes.COLUMN_USER_USERNAME + " =? and " + Constantes.COLUMN_USER_PASSWORD + "=?";

            cursor = db.rawQuery(query, new String[]{username, password});

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                usuario = new Usuario(
                        cursor.getInt(
                                cursor.getColumnIndex(Constantes.COLUMN_USER_ID)),
                        cursor.getString(
                                cursor.getColumnIndex(Constantes.COLUMN_USER_USERNAME)),
                        cursor.getString(
                                cursor.getColumnIndex(Constantes.COLUMN_USER_PASSWORD)),
                        cursor.getString(
                                cursor.getColumnIndex(Constantes.COLUMN_USER_EMAIL)),
                        cursor.getString(
                                cursor.getColumnIndex(Constantes.COLUMN_USER_NOMBRE))
                );
            }
            db.close();
        } finally {
            db.close();

        }
        return usuario;
    }

    public void addUser(Usuario usuario) {
        SQLiteDatabase db = conexion.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(Constantes.COLUMN_USER_USERNAME, usuario.getUsername());
            values.put(Constantes.COLUMN_USER_PASSWORD, usuario.getPassword());


            db.insert(Constantes.TABLE_USER, null, values);
            db.close();
        } finally {
            db.close();
        }
    }
}
