package pe.lucky.xplora.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.lucky.xplora.R;
import pe.lucky.xplora.model.Producto;


public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    List<Producto> listProducto;
    ItemClickListener listener;

    public ProductoAdapter(List<Producto> listProducto, ItemClickListener listener) {
        this.listProducto = listProducto;
        this.listener = listener;
    }

    public void agregar(ArrayList<Producto> producto) {
        listProducto.clear();
        listProducto.addAll(producto);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_producto_item, null, false);
        final ViewHolder mViewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Producto producto = listProducto.get(position);

        CharSequence precioCosto = (CharSequence) String.valueOf(producto.getPrecioCosto());
        CharSequence precioRvta = (CharSequence) String.valueOf(producto.getPrecioRvta());
        CharSequence stock = (CharSequence) String.valueOf(producto.getStock());

        holder.sku.setText((CharSequence) producto.getSku());
        holder.precioCosto.setText(precioCosto);
        holder.precioRvta.setText(precioRvta);
        holder.stock.setText(stock);


    }

    @Override
    public int getItemCount() {
        Log.v(ProductoAdapter.class.getSimpleName(), "" + listProducto.size());
        return listProducto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productoId, sku, precioCosto, precioRvta, stock, tiendaId;

        public ViewHolder(View itemView) {
            super(itemView);

            sku = itemView.findViewById(R.id.edtSkuPrecio);
            precioCosto = itemView.findViewById(R.id.edtCostoPrecio);
            precioRvta = itemView.findViewById(R.id.edtMvtaPrecio);
            stock = itemView.findViewById(R.id.edtStockPrecio);

        }

    }


}
