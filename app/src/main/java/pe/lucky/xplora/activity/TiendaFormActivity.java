package pe.lucky.xplora.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.lucky.xplora.DataBaseHelper;
import pe.lucky.xplora.R;
import pe.lucky.xplora.model.Tienda;
import pe.lucky.xplora.model.Usuario;
import pe.lucky.xplora.util.Constantes;

public class TiendaFormActivity extends AppCompatActivity {

    EditText edtNombreTienda, edtDireccionTienda;
    Button btnGuardar;

    DataBaseHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tienda);

        edtNombreTienda = findViewById(R.id.edtNombreTienda);
        edtDireccionTienda = findViewById(R.id.edtDireccionTienda);
        btnGuardar = findViewById(R.id.btnGuardarTienda);

        conn = new DataBaseHelper(getApplicationContext(), DataBaseHelper.DATABASE_NAME, null, DataBaseHelper.DATABASE_VERSION);
        registrarTienda();

    }


    private void registrarTienda() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    addTienda(new Tienda(edtNombreTienda.getText().toString(), edtDireccionTienda.getText().toString()));
                    Toast.makeText(TiendaFormActivity.this, "¡Se creo la nueva tienda con exito!", Toast.LENGTH_SHORT).show();
                    edtNombreTienda.setText("");
                    edtDireccionTienda.setText("");
                } else {
                    Toast.makeText(TiendaFormActivity.this, "Los campos estan vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void addTienda(Tienda tienda) {
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constantes.COLUMN_TIENDA_NOMBRE, tienda.getNombre());
        values.put(Constantes.COLUMN_TIENDA_DIRECCION, tienda.getDireccion());

        // Inserting Row
        db.insert(Constantes.TABLE_TIENDA, null, values);
        db.close(); // Closing database connection

    }

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(edtNombreTienda.getText().toString()) || TextUtils.isEmpty(edtDireccionTienda.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }


}
