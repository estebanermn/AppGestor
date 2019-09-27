package pe.lucky.xplora.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pe.lucky.xplora.model.Tienda;
import pe.lucky.xplora.util.Constantes;

public class TiendaSQL {

    private DataBaseHelper conexion;

    public TiendaSQL(Context context) {

        conexion = new DataBaseHelper(context);
    }

    public ArrayList<Tienda> listarTienda() {
        ArrayList<Tienda> lista = new ArrayList<>();
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery
                ("select * from " + Constantes.TABLE_TIENDA + "", null);
        if (cursor.moveToFirst()) {
            do {
                Tienda tienda = new Tienda(
                        cursor.getInt(
                                cursor.getColumnIndex(Constantes.COLUMN_TIENDA_ID)),
                        cursor.getString(
                                cursor.getColumnIndex(Constantes.COLUMN_TIENDA_NOMBRE)),
                        cursor.getString(
                                cursor.getColumnIndex(Constantes.COLUMN_TIENDA_DIRECCION)),
                        cursor.getString(
                                cursor.getColumnIndex(Constantes.COLUMN_TIENDA_LATITUD)),
                        cursor.getString(
                                cursor.getColumnIndex(Constantes.COLUMN_TIENDA_LONGITUD))
                );
                lista.add(tienda);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public void agregarTienda(Tienda tienda) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.COLUMN_TIENDA_NOMBRE, tienda.getNombre());
        contentValues.put(Constantes.COLUMN_TIENDA_DIRECCION, tienda.getDireccion());
//        contentValues.put(Constantes.COLUMN_TIENDA_LATITUD, tienda.getLatitud());
//        contentValues.put(Constantes.COLUMN_TIENDA_LONGITUD, tienda.getLongitud());
        db.insert(Constantes.TABLE_TIENDA, null, contentValues);
    }

    public void actualizarTienda(Tienda tienda) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.COLUMN_TIENDA_NOMBRE, tienda.getNombre());
        contentValues.put(Constantes.COLUMN_TIENDA_DIRECCION, tienda.getDireccion());
        db.update(Constantes.TABLE_TIENDA,
                contentValues,
                "" + Constantes.COLUMN_TIENDA_ID + "=?",
                new String[]{String.valueOf(tienda.getTiendaId())});
    }

    public void eliminarTienda(int idTienda) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        db.delete(Constantes.TABLE_TIENDA,
                "" + Constantes.COLUMN_TIENDA_ID + "=?",
                new String[]{String.valueOf(idTienda)});
    }


}
