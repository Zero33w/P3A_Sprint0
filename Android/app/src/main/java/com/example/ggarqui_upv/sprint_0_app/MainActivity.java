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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    //declaramos objetos que usaremos para interactuar con los elementos del layout
    EditText edtIdMedicion,edtIdSensor,edtValorMedicion;
    Button btnAgregar, btnBuscar;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Vinculamos los objetos creados con los elementos del layout
        edtIdMedicion=(EditText) findViewById(R.id.edtIdMedicion);
        edtIdSensor=(EditText) findViewById(R.id.edtIdSensor);
        edtValorMedicion=(EditText) findViewById(R.id.edtValorMedicion);
        btnAgregar=(Button) findViewById(R.id.btnAgregar);
        btnBuscar=(Button) findViewById(R.id.btnBuscar);


        //el boton agregar tras ser clicado realiza la llamada a la funcion ejecutarServicio
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardararMedidaEnBD("http://192.168.1.141:80/sprint0/insertar_medida.php");
                //ejecutarServicio("http://192.168.1.153:80/sprint0/insertar_medida.php");
            }
        });

        //el boton buscar tras ser clicado realiza la llamada a la funcion buscarMedida
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuscarMedidaPorID("http://192.168.1.141:80/sprint0/buscar_medida.php?idMedicion="+edtIdMedicion.getText()+"");
               // buscarMedida("http://192.168.1.153:80/sprint0/buscar_medida.php?idMedicion="+edtIdMedicion.getText()+"");
            }
        });
    }

    //La siguiente funcion realiza la consulta para subir los datos en los distintos campos del layout como una nueva entrada en la base de datos
    //String -> EjecutarServicio()
    protected void GuardararMedidaEnBD(String URL){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
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
                parametros.put("momentoMedicion", ObtenerFechaYHoraActual());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //La siguiente funcion realiza la consulta para solicitar la medida de la tabla medida en la base de datos indicada a traves de la isMedicion que le pasas
    private void BuscarMedidaPorID(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        edtIdSensor.setText(jsonObject.getString("idSensor"));
                        edtValorMedicion.setText(jsonObject.getString("valorMedicion"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    //La siguiente funcion sirve para capturar la fecha y hora en formato timestamp del momento en que es llamada
    public String ObtenerFechaYHoraActual(){
        TimeZone tz = TimeZone.getTimeZone("GMT+2");
        Calendar calendar= Calendar.getInstance(tz);
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(tz);
        String timestamp=simpleDateFormat.format(calendar.getTime());
        return timestamp;
    }
}