package pe.lucky.xplora.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.lucky.xplora.R;
import pe.lucky.xplora.adapter.ItemClickListener;
import pe.lucky.xplora.adapter.ProductoAdapter;
import pe.lucky.xplora.model.Producto;
import pe.lucky.xplora.sqlite.ProductoSQL;

import static android.support.constraint.Constraints.TAG;

public class ProductoFragment extends Fragment {

    TextView edtSku, edtPrecioCosto, edtPrecioRvta, edtStock;

    private List<Producto> listProducto;
    RecyclerView recyclerViewProducto;
    private ProductoAdapter adapter;

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

        setHasOptionsMenu(true);
        initView(view);
        initObjects();

    }

    private void initView(View view) {
        edtSku = view.findViewById(R.id.edtSkuPrecio);
        edtPrecioCosto = view.findViewById(R.id.edtCostoPrecio);
        edtPrecioRvta = view.findViewById(R.id.edtMvtaPrecio);
        edtStock = view.findViewById(R.id.edtStockPrecio);

        recyclerViewProducto = view.findViewById(R.id.recyclerViewPrecio);

        ((NavigationActivity) getActivity()).getSupportActionBar().setTitle("Precios");

    }


    private void initObjects() {

        listProducto = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewProducto.setLayoutManager(layoutManager);

        recyclerViewProducto.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProducto.setHasFixedSize(true);



        adapter = new ProductoAdapter(listProducto, new ItemClickListener() {
            @Override
            public void onItemClick(View v, final int position) {
                Log.d(TAG, "clicked position:" + position);
                final Producto producto = listProducto.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Acción");
                builder.setMessage("¿Qué desea hacer?");
                builder.setPositiveButton("Actualizar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getContext(), ProductoFormActivity.class);
                                intent.putExtra("productoId", producto.getProductoId());

                                getContext().startActivity(intent);
                            }
                        });
                builder.setNegativeButton("Eliminar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ProductoSQL productoSQL = new ProductoSQL(getContext());
                                productoSQL.delete(producto.getProductoId());
                                listProducto.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        });
                builder.setNeutralButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();

            }
        });

        recyclerViewProducto.setAdapter(adapter);


    }

    @Override
    public void onStart() {
        super.onStart();
        ProductoSQL productoSQL = new ProductoSQL(getContext());
        int tiendaId =  getArguments().getInt("tiendaId");
        adapter.agregar(productoSQL.getProductoByTienda(tiendaId));
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_producto, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item_add_producto:
                Intent intent = new Intent(getContext(), ProductoFormActivity.class);
                getContext().startActivity(intent);
                return true;
        }
        return false;
    }


}
