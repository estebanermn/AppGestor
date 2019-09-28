package pe.lucky.xplora.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.lucky.xplora.R;
import pe.lucky.xplora.activity.MapaFragment;
import pe.lucky.xplora.activity.MapsActivity;
import pe.lucky.xplora.activity.ProductoFormActivity;
import pe.lucky.xplora.model.Tienda;

public class TiendaAdapter extends RecyclerView.Adapter<TiendaAdapter.ViewHolder> {

    List<Tienda> listTienda;
    private OnItemClickListener listener;
    Context context;
    Activity activity;


    public TiendaAdapter(List<Tienda> listTienda, Context context, OnItemClickListener listener) {
        this.listTienda = listTienda;
        this.context = context;
        this.listener = listener;
    }

    public void agregar(ArrayList<Tienda> tienda) {
        listTienda.clear();
        listTienda.addAll(tienda);
        notifyDataSetChanged();
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.nombre.setText(listTienda.get(position).getNombre().toString());
        holder.direccion.setText(listTienda.get(position).getDireccion().toString());

        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "termina :  " + position, Toast.LENGTH_SHORT).show();
                int tiendaId = listTienda.get(position).getTiendaId();


                replaceFragmentMapa(v, tiendaId);
            }
        });

    }

    private void replaceFragmentMapa(View v, int tiendaId) {

        AppCompatActivity activity = (AppCompatActivity) v.getContext();

        Fragment myFragment = new MapsActivity();
        Bundle args = new Bundle();
        args.putInt("tiendaId", tiendaId);
        myFragment.setArguments(args);

        activity.getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, myFragment).addToBackStack(null).commit();

    }

//    private void replaceFragmentMapa(View v , int tiendaId){
//        Intent intent = new Intent(context, MapsActivity.class);
//        intent.putExtra("productoId", tiendaId);
//        context.startActivity(intent);
//    }

    @Override
    public int getItemCount() {
        Log.v(TiendaAdapter.class.getSimpleName(), "" + listTienda.size());
        return listTienda.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, direccion;
        ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.rowNombreTienda);
            direccion = itemView.findViewById(R.id.rowDireccionTienda);
            imagen = itemView.findViewById(R.id.imvMapa);
            itemView.setOnCreateContextMenuListener(null);

        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}
