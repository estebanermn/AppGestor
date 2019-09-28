package pe.lucky.xplora.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pe.lucky.xplora.R;
import pe.lucky.xplora.model.Tienda;
import pe.lucky.xplora.sqlite.TiendaSQL;

public class MapsActivity extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_maps, container, false);


        SupportMapFragment mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        TiendaSQL tiendaSQL = new TiendaSQL(getContext());
        int tiendaId = getArguments().getInt("tiendaId");
        Tienda tienda = tiendaSQL.getTiendaById(tiendaId);

        // Add a marker in Sydney and move the camera
        LatLng latlng = new LatLng( tienda.getLatitud(), tienda.getLongitud());
        mMap.addMarker(new MarkerOptions().position(latlng).title(tienda.getNombre()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
}
