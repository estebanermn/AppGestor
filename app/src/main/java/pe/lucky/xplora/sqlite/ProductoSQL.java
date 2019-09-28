package pe.lucky.xplora.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;


import pe.lucky.xplora.model.Producto;
import pe.lucky.xplora.util.Constantes;

public class ProductoSQL {

    private DataBaseHelper conexion;

    public ProductoSQL(Context context) {

        conexion = new DataBaseHelper(context);
    }

    public ArrayList<Producto> getProductoByTienda(int tiendaId) {
        ArrayList<Producto> lista = new ArrayList<>();
        SQLiteDatabase db = conexion.getReadableDatabase();
        String query = "select * from " + Constantes.TABLE_PRODUCTO + " where " + Constantes.COLUMN_PRODUCTO_TIENDA_ID + " =?";
        try {
            Cursor cursor = db.rawQuery
                    (query, new String[]{String.valueOf(tiendaId)});

            if (cursor.moveToFirst()) {
                do {
                    Producto producto = new Producto(
                            cursor.getInt(
                                    cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_ID)),
                            cursor.getString(
                                    cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_SKU)),
                            cursor.getDouble(
                                    cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_PRECIO_COSTO)),
                            cursor.getDouble(
                                    cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_PRECIO_RVTA)),
                            cursor.getInt(
                                    cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_STOCK)),
                            cursor.getInt(
                                    cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_TIENDA_ID))
                    );
                    lista.add(producto);
                } while (cursor.moveToNext());
            }
        } finally {
            db.close();
        }
        return lista;
    }

    public void create(Producto producto) {
        SQLiteDatabase db = conexion.getWritableDatabase();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constantes.COLUMN_PRODUCTO_SKU, producto.getSku());
            contentValues.put(Constantes.COLUMN_PRODUCTO_PRECIO_COSTO, producto.getPrecioCosto());
            contentValues.put(Constantes.COLUMN_PRODUCTO_PRECIO_RVTA, producto.getPrecioRvta());
            contentValues.put(Constantes.COLUMN_PRODUCTO_STOCK, producto.getStock());
            contentValues.put(Constantes.COLUMN_PRODUCTO_TIENDA_ID, producto.getTiendaId());
            db.insert(Constantes.TABLE_PRODUCTO, null, contentValues);
        } finally {
            db.close();
        }

    }

    public void update(Producto producto) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constantes.COLUMN_PRODUCTO_SKU, producto.getSku());
            contentValues.put(Constantes.COLUMN_PRODUCTO_PRECIO_COSTO, producto.getPrecioCosto());
            contentValues.put(Constantes.COLUMN_PRODUCTO_PRECIO_RVTA, producto.getPrecioRvta());
            contentValues.put(Constantes.COLUMN_PRODUCTO_STOCK, producto.getStock());
            contentValues.put(Constantes.COLUMN_PRODUCTO_TIENDA_ID, producto.getTiendaId());
            db.update(Constantes.TABLE_PRODUCTO,
                    contentValues,
                    "" + Constantes.COLUMN_PRODUCTO_ID + "=?",
                    new String[]{String.valueOf(producto.getProductoId())});

        } finally {
            db.close();
        }
    }

    public void delete(int idProducto) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        try {
            db.delete(Constantes.TABLE_PRODUCTO,
                    "" + Constantes.COLUMN_PRODUCTO_ID + "=?",
                    new String[]{String.valueOf(idProducto)});
        } finally {
            db.close();
        }

    }

    public Producto getProductoById(int productoId) {

        Producto producto = new Producto();

        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor cursor = null;
        String query = "select * from " + Constantes.TABLE_PRODUCTO + " where " + Constantes.COLUMN_PRODUCTO_ID + " =?";

        try {
            cursor = db.rawQuery(query, new String[]{String.valueOf(productoId)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                producto = new Producto(
                        cursor.getInt(
                                cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_ID)),
                        cursor.getString(
                                cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_SKU)),
                        cursor.getDouble(
                                cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_PRECIO_COSTO)),
                        cursor.getDouble(
                                cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_PRECIO_RVTA)),
                        cursor.getInt(
                                cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_STOCK)),
                        cursor.getInt(
                                cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_TIENDA_ID))
                );
            }

        } finally {
            cursor.close();
        }

        return producto;
    }

}
