package pe.lucky.xplora.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.lucky.xplora.R;
import pe.lucky.xplora.model.Producto;
import pe.lucky.xplora.sqlite.ProductoSQL;

public class ProductoFormActivity extends AppCompatActivity {

    EditText edtSkuProducto, edtPrecioCostoProducto, edtPrecioRvtaProducto, edtStockProducto;
    Button btnGuardarProducto;
   /// Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_producto);

        initView();
        btnGuardarProducto.setTag(0);

        if (getIntent().hasExtra("productoId")) {

            ProductoSQL productoSQL = new ProductoSQL(this);

            int productoId = getIntent().getIntExtra("productoId", 0);

            Producto producto = productoSQL.getProductoById(productoId);

            edtSkuProducto.setText(producto.getSku());
            edtPrecioCostoProducto.setText(String.valueOf(producto.getPrecioCosto()));
            edtPrecioRvtaProducto.setText(String.valueOf(producto.getPrecioRvta()));
            edtStockProducto.setText(String.valueOf(producto.getStock()));

            btnGuardarProducto.setTag(productoId);
        }


    }

    private void initView() {

        edtSkuProducto = findViewById(R.id.edtSkuProducto);
        edtPrecioCostoProducto = findViewById(R.id.edtPrecioCostoProducto);
        edtPrecioRvtaProducto = findViewById(R.id.edtPrecioRvtaProducto);
        edtStockProducto = findViewById(R.id.edtStockProducto);

        btnGuardarProducto = findViewById(R.id.btnGuardarProducto);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnGuardarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sku = edtSkuProducto.getText().toString();
                Double precioCosto = Double.parseDouble(edtPrecioCostoProducto.getText().toString());
                Double precioRvta = Double.parseDouble(edtPrecioRvtaProducto.getText().toString());
                int stock = Integer.parseInt(edtStockProducto.getText().toString());

                Producto objPersona = new Producto(0, sku, precioCosto, precioRvta, stock);

                ProductoSQL productoSQL = new ProductoSQL(getApplicationContext());
                int idProducto = (int) btnGuardarProducto.getTag();
                if (idProducto == 0) {
                    productoSQL.create(objPersona);
                    Toast.makeText(ProductoFormActivity.this,
                            "Se registró correctamente",
                            Toast.LENGTH_SHORT).show();
                } else {
                    objPersona.setProductoId(idProducto);
                    productoSQL.update(objPersona);
                    Toast.makeText(ProductoFormActivity.this,
                            "Se actualizó correctamente",
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
