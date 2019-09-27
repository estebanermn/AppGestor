package pe.lucky.xplora.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.lucky.xplora.DataBaseHelper;
import pe.lucky.xplora.R;
import pe.lucky.xplora.adapter.ItemClickListener;
import pe.lucky.xplora.adapter.ProductoAdapter;
import pe.lucky.xplora.model.Producto;
import pe.lucky.xplora.util.Constantes;

import static android.support.constraint.Constraints.TAG;

public class ProductoFragment extends Fragment {

    TextView edtSku, edtPrecioCosto, edtPrecioRvta, edtStock;
    private Toolbar mTopToolbar;

    private List<Producto> listProducto;
    RecyclerView recyclerViewProducto;
    private ProductoAdapter adapter;

    DataBaseHelper conn;

    public ProductoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_producto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initObjects();
//        mTopToolbar = view.findViewById(R.id.toolbar);
//        setSupportActionBar(mTopToolbar);
//        getSupportActionBar().setTitle("Precios");

    }

    private void initView(View view) {
        edtSku = view.findViewById(R.id.edtSkuPrecio);
        edtPrecioCosto = view.findViewById(R.id.edtCostoPrecio);
        edtPrecioRvta = view.findViewById(R.id.edtMvtaPrecio);
        edtStock = view.findViewById(R.id.edtStockPrecio);

        recyclerViewProducto = view.findViewById(R.id.recyclerViewPrecio);

        ((NavigationActivity) getActivity()).getSupportActionBar().setTitle("Your Title");

    }


    private void initObjects() {

        listProducto = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewProducto.setLayoutManager(layoutManager);

        recyclerViewProducto.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProducto.setHasFixedSize(true);

        adapter = new ProductoAdapter(listProducto, new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                long postId = listProducto.get(position).getProductoId();
//                Intent intent = new Intent(getContext(), ProductoActivity.class);
//                getContext().startActivity(intent);
            }
        });

        recyclerViewProducto.setAdapter(adapter);


        conn = new DataBaseHelper(getContext(), DataBaseHelper.DATABASE_NAME,
                null, DataBaseHelper.DATABASE_VERSION);

        showProductoList();

    }

    private void showProductoList() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listProducto.clear();
                listProducto.addAll(getAllProducto());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

    public List<Producto> getAllProducto() {
        // array of columns to fetch
        String[] columns = {
                Constantes.COLUMN_PRODUCTO_ID,
                Constantes.COLUMN_PRODUCTO_SKU,
                Constantes.COLUMN_PRODUCTO_PRECIO_COSTO,
                Constantes.COLUMN_PRODUCTO_PRECIO_RVTA,
                Constantes.COLUMN_PRODUCTO_STOCK,
                Constantes.COLUMN_PRODUCTO_TIENDA_ID
        };
        // sorting orders
        String sortOrder =
                Constantes.COLUMN_PRODUCTO_SKU + " ASC";
        List<Producto> productoList = new ArrayList<Producto>();

        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.query(Constantes.TABLE_PRODUCTO, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Producto producto = new Producto();
                producto.setProductoId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_ID))));
                producto.setSku(cursor.getString(cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_SKU)));
                producto.setPrecioCosto(Double.parseDouble(cursor.getString(cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_PRECIO_COSTO))));
                producto.setPrecioRvta(Double.parseDouble(cursor.getString(cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_PRECIO_RVTA))));
                producto.setStock(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_STOCK))));
                producto.setTiendaId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constantes.COLUMN_PRODUCTO_TIENDA_ID))));

                // Adding user record to list
                productoList.add(producto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return productoList;
    }


}
