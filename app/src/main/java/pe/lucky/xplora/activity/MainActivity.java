package pe.lucky.xplora.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.lucky.xplora.sqlite.DataBaseHelper;
import pe.lucky.xplora.R;
import pe.lucky.xplora.model.Usuario;
import pe.lucky.xplora.sqlite.UsuarioSQL;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnIngresar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnIngresar = findViewById(R.id.btnIngresar);
//        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsuarioSQL usuarioSQL = new UsuarioSQL(getApplicationContext());
                if (!emptyValidation()) {
                    Usuario usuario = usuarioSQL.queryUser(edtUsername.getText().toString(), edtPassword.getText().toString());
                    if (usuario != null) {


                        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("usuario", usuario);
//                        intent.putExtras(bundle);
                        intent.putExtra("usuario", usuario);
                        startActivity(intent);

                        Toast.makeText(MainActivity.this, "Bienvenido " + usuario.getNombre(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Usuario no encontrado" + usuario.getUsername(), Toast.LENGTH_SHORT).show();
                        edtPassword.setText("");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Campos vacíos", Toast.LENGTH_SHORT).show();

                }
            }
        });

//        btnRegistrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!emptyValidation()) {
//                    dbHelper.addUser(new Usuario(edtUsername.getText().toString(), edtPassword.getText().toString()));
//                    Toast.makeText(MainActivity.this, "Added User", Toast.LENGTH_SHORT).show();
//                    edtUsername.setText("");
//                    edtPassword.setText("");
//                } else {
//                    Toast.makeText(MainActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }


    private boolean emptyValidation() {
        if (TextUtils.isEmpty(edtUsername.getText().toString()) || TextUtils.isEmpty(edtPassword.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }


}
