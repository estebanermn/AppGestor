package pe.lucky.xplora.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.lucky.xplora.R;
import pe.lucky.xplora.model.Tienda;

public class TiendaAdapter extends RecyclerView.Adapter<TiendaAdapter.ViewHolder> {

    List<Tienda> listTienda;
    ItemClickListener listener;

    public TiendaAdapter(List<Tienda> listTienda) {
        this.listTienda = listTienda;
    }

    public TiendaAdapter(List<Tienda> listTienda, ItemClickListener listener) {
        this.listTienda = listTienda;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tienda_item, null, false);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombre.setText(listTienda.get(position).getNombre().toString());
        holder.direccion.setText(listTienda.get(position).getDireccion().toString());
    }

    @Override
    public int getItemCount() {
        Log.v(TiendaAdapter.class.getSimpleName(), "" + listTienda.size());
        return listTienda.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, direccion;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.rowNombreTienda);
            direccion = itemView.findViewById(R.id.rowDireccionTienda);
        }

    }


}
