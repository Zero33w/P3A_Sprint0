package com.example.ggarqui_upv.sprint_0_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //creamos objetos que usaremos para interactuar con los elementos del layout
    EditText edtIdMedicion,edtIdSensor,edtValorMedicion;
    Button btnAgregar, btnBuscar;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Vinculamos los objetos creadas con los elementos del layout
        edtIdMedicion=(EditText) findViewById(R.id.edtIdMedicion);
        edtIdSensor=(EditText) findViewById(R.id.edtIdSensor);
        edtValorMedicion=(EditText) findViewById(R.id.edtValorMedicion);
        btnAgregar=(Button) findViewById(R.id.btnAgregar);
        btnBuscar=(Button) findViewById(R.id.btnBuscar);

        //el boton agregar tras ser clicado realiza la llamada a la funcion ejecutarServicio
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://192.168.1.153:80/sprint0/insertar_medida.php");
            }
        });
    }

    //La siguiente funcion realiza la consulta para subir los datos en los distintos campos del layout como una nueva entrada en la base de datos
    protected void ejecutarServicio(String enlace){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, enlace, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"OPERACION EXITOSA",Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("idMedicion",edtIdMedicion.getText().toString());
                parametros.put("idSensor",edtIdSensor.getText().toString());
                parametros.put("valorMedicion",edtValorMedicion.getText().toString());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}