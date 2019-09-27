package pe.lucky.xplora.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.lucky.xplora.model.Producto;
import pe.lucky.xplora.sqlite.DataBaseHelper;
import pe.lucky.xplora.R;
import pe.lucky.xplora.model.Tienda;
import pe.lucky.xplora.sqlite.ProductoSQL;
import pe.lucky.xplora.sqlite.TiendaSQL;
import pe.lucky.xplora.util.Constantes;

public class TiendaFormActivity extends AppCompatActivity {

    EditText edtNombreTienda, edtDireccionTienda;
    Button btnGuardarTienda;

    DataBaseHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tienda);

        initView();
        btnGuardarTienda.setTag(0);


    }

    private void initView() {

        edtNombreTienda = findViewById(R.id.edtNombreTienda);
        edtDireccionTienda = findViewById(R.id.edtDireccionTienda);
        btnGuardarTienda = findViewById(R.id.btnGuardarTienda);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnGuardarTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edtNombreTienda.getText().toString();
                String direccion = edtDireccionTienda.getText().toString();

                Tienda objTienda = new Tienda(0, nombre, direccion, "3", "4");

                TiendaSQL tiendaSQL = new TiendaSQL(getApplicationContext());
                int idTienda = (int) btnGuardarTienda.getTag();
                if (idTienda == 0) {
                    tiendaSQL.agregarTienda(objTienda);
                    Toast.makeText(TiendaFormActivity.this,
                            "Se registró correctamente",
                            Toast.LENGTH_SHORT).show();
                } else {
                    objTienda.setTiendaId(idTienda);
                    tiendaSQL.actualizarTienda(objTienda);
                    Toast.makeText(TiendaFormActivity.this,
                            "Se actualizó correc    tamente",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


//    private boolean emptyValidation() {
//        if (TextUtils.isEmpty(edtNombreTienda.getText().toString()) || TextUtils.isEmpty(edtDireccionTienda.getText().toString())) {
//            return true;
//        } else {
//            return false;
//        }
//    }


}
