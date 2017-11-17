package com.taes.iair.iair;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         atualizarQualidadeAr();

        /*SharedPreferences sharedPref = getApplication().getSharedPreferences(
                getString(R.string.FAVOURITE_LOCATION), Context.MODE_PRIVATE);*/

        //SharedPreferences prefs = this.getSharedPreferences(
                //"favorito", Context.MODE_PRIVATE);
       // prefs.edit().putString("favorito", "Lisboa").apply();

    }

    public void atualizarQualidadeAr(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.thingspeak.com/channels/365262/feeds.json?results=2";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject mainObject = new JSONObject(response);
                            JSONArray feeds = mainObject.getJSONArray("feeds");
                            JSONObject ultimaInfo = feeds.getJSONObject(feeds.length()-1);

                            atualizarTextBoxes(ultimaInfo.getInt("field3"),
                                    ultimaInfo.getInt("field2"),
                                    ultimaInfo.getInt("field4"));

                              } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
}

    public void atualizarTextBoxes( int temp,int pressAtm,int hum){
        final TextView txtHum = findViewById(R.id.textViewHumRel);
        final TextView txtPressAtm = findViewById(R.id.textViewPressAtm);
        final TextView txtTemp = findViewById(R.id.textViewTempAr);

        txtTemp.setText(String.valueOf(temp+" ºC"));
        txtPressAtm.setText(String.valueOf(pressAtm+" atm"));
        txtHum.setText(String.valueOf(hum+" %"));

    }

    public void onClickLocais(View view) {
        Intent it = new Intent(this, LocaisActivity.class);

        startActivity(it);
    }

    public void onClickSensores(View view) {
        Intent it = new Intent(this, SensoresActivity.class);

        startActivity(it);
    }
    }

