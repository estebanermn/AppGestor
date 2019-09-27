package pe.lucky.xplora.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pe.lucky.xplora.DataBaseHelper;
import pe.lucky.xplora.R;
import pe.lucky.xplora.adapter.ItemClickListener;
import pe.lucky.xplora.adapter.TiendaAdapter;
import pe.lucky.xplora.model.Tienda;
import pe.lucky.xplora.util.Constantes;

import static android.support.constraint.Constraints.TAG;


public class TiendaFragment extends Fragment implements View.OnClickListener {
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

    DataBaseHelper conn;

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

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Punto de Venta");
        setHasOptionsMenu(true);

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

        recyclerViewTienda = view.findViewById(R.id.recyclerViewTienda);
        initObjects();
        showTiendaList();

    }

    private void initObjects() {

        listTienda = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerViewTienda.setLayoutManager(layoutManager);

        recyclerViewTienda.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTienda.setHasFixedSize(true);

        adapter = new TiendaAdapter(listTienda, new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                long postId = listTienda.get(position).getTiendaId();
//                Intent intent = new Intent(getContext(), ProductoActivity.class);
//                getContext().startActivity(intent);
                replaceFragment();

            }
        });

        recyclerViewTienda.setAdapter(adapter);

        conn = new DataBaseHelper(getActivity().getApplicationContext(), DataBaseHelper.DATABASE_NAME,
                null, DataBaseHelper.DATABASE_VERSION);

        showTiendaList();
    }

    private void replaceFragment() {
        Fragment newFragment = new ProductoFragment();

        FragmentManager mFragmentManager = getFragmentManager();
        if (mFragmentManager != null) {
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.contenedor, newFragment);
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        }
    }


    private void showTiendaList() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listTienda.clear();
                listTienda.addAll(getAllTienda());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

    public List<Tienda> getAllTienda() {
        // array of columns to fetch
        String[] columns = {
                Constantes.COLUMN_TIENDA_ID,
                Constantes.COLUMN_TIENDA_NOMBRE,
                Constantes.COLUMN_TIENDA_DIRECCION

        };
        // sorting orders
        String sortOrder =
                Constantes.COLUMN_TIENDA_NOMBRE + " ASC";
        List<Tienda> tiendaList = new ArrayList<Tienda>();

        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.query(Constantes.TABLE_TIENDA, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Tienda tienda = new Tienda();
                tienda.setTiendaId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constantes.COLUMN_TIENDA_ID))));
                tienda.setNombre(cursor.getString(cursor.getColumnIndex(Constantes.COLUMN_TIENDA_NOMBRE)));
                tienda.setDireccion(cursor.getString(cursor.getColumnIndex(Constantes.COLUMN_TIENDA_DIRECCION)));
                // Adding user record to list
                tiendaList.add(tienda);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return tiendaList;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_tienda, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.add_tienda:
                Intent intent = new Intent(getActivity(), TiendaFormActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
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

    @Override
    public void onClick(View v) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
