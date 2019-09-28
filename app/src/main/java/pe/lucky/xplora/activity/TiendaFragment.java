package pe.lucky.xplora.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.lucky.xplora.R;
import pe.lucky.xplora.adapter.TiendaAdapter;
import pe.lucky.xplora.model.Tienda;
import pe.lucky.xplora.sqlite.TiendaSQL;

import static android.support.constraint.Constraints.TAG;


public class TiendaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<Tienda> listTienda;
    RecyclerView recyclerViewTienda;
    private TiendaAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public TiendaFragment() {
    }

    public static TiendaFragment newInstance(String param1, String param2) {
        TiendaFragment fragment = new TiendaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tienda, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Punto de Venta");

        recyclerViewTienda = view.findViewById(R.id.recyclerViewTienda);
        initObjects();


    }

    private void initObjects() {

        listTienda = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerViewTienda.setLayoutManager(layoutManager);

        recyclerViewTienda.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTienda.setHasFixedSize(true);
        registerForContextMenu(recyclerViewTienda);

        adapter = new TiendaAdapter(listTienda, getContext(), new TiendaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Log.d(TAG, "clicked position:" + position);
                int tiendaId = listTienda.get(position).getTiendaId();

                adapter.notifyItemChanged(position);
                replaceFragment(tiendaId);
            }
        });


        recyclerViewTienda.setAdapter(adapter);

    }

    private void replaceFragment(int tiendaId) {
        Fragment newFragment = new ProductoFragment();

        FragmentManager mFragmentManager = getFragmentManager();
        if (mFragmentManager != null) {

            Bundle args = new Bundle();
            args.putInt("tiendaId", tiendaId);
            newFragment.setArguments(args);

            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.contenedor, newFragment);
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        TiendaSQL tiendaSQL = new TiendaSQL(getContext());
        adapter.agregar(tiendaSQL.getTienda());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_tienda, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//
//            case R.id.add_tienda:
//                Intent intent = new Intent(getActivity(), TiendaFormActivity.class);
//                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private void delete(int position) {
        listTienda.remove(position);
        adapter.notifyItemRemoved(position);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
