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

    public ArrayList<Tienda> getTienda() {
        ArrayList<Tienda> lista = new ArrayList<>();
        SQLiteDatabase db = conexion.getReadableDatabase();

        try {

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
                            cursor.getDouble(
                                    cursor.getColumnIndex(Constantes.COLUMN_TIENDA_LATITUD)),
                            cursor.getDouble(
                                    cursor.getColumnIndex(Constantes.COLUMN_TIENDA_LONGITUD))
                    );
                    lista.add(tienda);
                } while (cursor.moveToNext());
            }
        } finally {
            db.close();
        }
        return lista;
    }

    public void create(Tienda tienda) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constantes.COLUMN_TIENDA_NOMBRE, tienda.getNombre());
            contentValues.put(Constantes.COLUMN_TIENDA_DIRECCION, tienda.getDireccion());
            db.insert(Constantes.TABLE_TIENDA, null, contentValues);
        } finally {
            db.close();
        }
    }

    public void update(Tienda tienda) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        try {


            ContentValues contentValues = new ContentValues();
            contentValues.put(Constantes.COLUMN_TIENDA_NOMBRE, tienda.getNombre());
            contentValues.put(Constantes.COLUMN_TIENDA_DIRECCION, tienda.getDireccion());
            db.update(Constantes.TABLE_TIENDA,
                    contentValues,
                    "" + Constantes.COLUMN_TIENDA_ID + "=?",
                    new String[]{String.valueOf(tienda.getTiendaId())});
        } finally {
            db.close();
        }

    }

    public void delete(int idTienda) {


        SQLiteDatabase db = conexion.getWritableDatabase();

        try {
            db.delete(Constantes.TABLE_TIENDA,
                    "" + Constantes.COLUMN_TIENDA_ID + "=?",
                    new String[]{String.valueOf(idTienda)});
        } finally {
            db.close();
        }
    }


    public Tienda getTiendaById(int tiendaId) {

        Tienda tienda = new Tienda();

        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor cursor = null;
        String query = "select * from " + Constantes.TABLE_TIENDA + " where " + Constantes.COLUMN_TIENDA_ID + " =?";

        try {
            cursor = db.rawQuery(query, new String[]{String.valueOf(tiendaId)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                tienda = new Tienda(
                        cursor.getInt(
                                cursor.getColumnIndex(Constantes.COLUMN_TIENDA_ID)),
                        cursor.getString(
                                cursor.getColumnIndex(Constantes.COLUMN_TIENDA_NOMBRE)),
                        cursor.getString(
                                cursor.getColumnIndex(Constantes.COLUMN_TIENDA_DIRECCION)),
                        cursor.getDouble(
                                cursor.getColumnIndex(Constantes.COLUMN_TIENDA_LATITUD)),
                        cursor.getDouble(
                                cursor.getColumnIndex(Constantes.COLUMN_TIENDA_LONGITUD))
                );
            }

        } finally {
            cursor.close();
        }

        return tienda;
    }


}
