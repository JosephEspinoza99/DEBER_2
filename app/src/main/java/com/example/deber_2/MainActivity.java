package com.example.deber_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void btmostrar (View view){
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(" https://jsonplaceholder.typicode.com/users"
                ,datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }
    @Override
    public void processFinish(String result) throws JSONException {
        TextView Respuesta = (TextView) findViewById(R.id.respuesta);
        String lstUser = "";
        JSONArray JSONlista = new JSONArray(result);
        for (int i = 0; i < JSONlista.length(); i++) {
            JSONObject user = JSONlista.getJSONObject(i);
            String nombre = user.getString("name");
            String direccion = user.getString("email");
            String telefono = user.getString("phone");
            String website = user.getString("website");
            String compañia = user.getString("company");
            lstUser = lstUser + " (" + i +") "+ " " + nombre + ",  " + direccion+",  "+telefono+",  "+website+"\n";
            final int finalI = i;
            final String finalLstUser = lstUser;
            new android.os.Handler().postDelayed(new Runnable() {
                public void run() {
                    Respuesta.setText(finalLstUser);

                    if (finalI < JSONlista.length() - 1) {
                        Respuesta.append("\n");
                    }
                }
            }, (i + 1) * 500);
        }
    }
}