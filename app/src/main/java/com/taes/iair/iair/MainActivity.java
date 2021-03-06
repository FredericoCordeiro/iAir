package com.taes.iair.iair;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    String favorito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences prefs = this.getSharedPreferences("favorito", Context.MODE_PRIVATE);
        favorito = prefs.getString("favorito","Lisboa");


        TextView txtCidade = findViewById(R.id.textCidadeName);
        txtCidade.setText(favorito);
        atualizarQualidadeAr();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.item_update)
        {

            SharedPreferences prefs = this.getSharedPreferences("favorito", Context.MODE_PRIVATE);
            favorito = prefs.getString("favorito","Lisboa");


            TextView txtCidade = findViewById(R.id.textCidadeName);
            txtCidade.setText(favorito);
            atualizarQualidadeAr();

        }

        return super.onOptionsItemSelected(item);
    }

    public void atualizarQualidadeAr(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url=null;
        switch (favorito){
            case "Aveiro":
                url="https://api.thingspeak.com/channels/372029/feeds.json?results=1";
                break;
            case "Beja":
                url="https://api.thingspeak.com/channels/372033/feeds.json?results=1";
                break;
            case "Braga":
                url="https://api.thingspeak.com/channels/372035/feeds.json?results=1";
                break;
            case "Bragança":
                url="https://api.thingspeak.com/channels/372037/feeds.json?results=1";
                break;
            case "Castelo Branco":
                url="https://api.thingspeak.com/channels/372040/feeds.json?results=1";
                break;
            case "Coimbra":
                url="https://api.thingspeak.com/channels/372041/feeds.json?results=1";
                break;
            case "Évora":
                url="https://api.thingspeak.com/channels/372045/feeds.json?results=1";
                break;
            case "Faro":
                url="https://api.thingspeak.com/channels/372046/feeds.json?results=1";
                break;
            case "Guarda":
                url="https://api.thingspeak.com/channels/372047/feeds.json?results=1";
                break;
            case "Leiria":
                url="https://api.thingspeak.com/channels/372048/feeds.json?results=1";
                break;
            case "Lisboa":
                url="https://api.thingspeak.com/channels/372050/feeds.json?results=1";
                break;
            case "Portalegre":
                url="https://api.thingspeak.com/channels/372052/feeds.json?results=1";
                break;
            case "Porto":
                url="https://api.thingspeak.com/channels/372053/feeds.json?results=1";
                break;
            case "Santarém":
                url="https://api.thingspeak.com/channels/372058/feeds.json?results=1";
                break;
            case "Setúbal":
                url="https://api.thingspeak.com/channels/372059/feeds.json?results=1";
                break;
            case "Viana do Castelo":
                url="https://api.thingspeak.com/channels/372060/feeds.json?results=1";
                break;
            case "Vila Real":
                url="https://api.thingspeak.com/channels/372061/feeds.json?results=1";
                break;
            case "Viseu":
                url="https://api.thingspeak.com/channels/372062/feeds.json?results=1";
                break;
            default:
                break;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject mainObject = new JSONObject(response);
                            JSONArray feeds = mainObject.getJSONArray("feeds");
                            //JSONObject ultimaInfo = null;

                            ArrayList<JSONObject> list = new ArrayList<JSONObject>();
                            if (feeds != null) {
                                int len = feeds.length();
                                for (int i=0;i<len;i++){
                                    list.add((JSONObject) feeds.get(i));
                                }
                            }
                            for(int i= list.size()-1;i>=0;i--){
                                JSONObject atual;
                                atual= list.get(i);



                            }

                            JSONObject ultimaInfo = feeds.getJSONObject(feeds.length()-1);


                            atualizarTextBoxes(ultimaInfo.getInt("field3"),
                                    ultimaInfo.getInt("field2"),
                                    ultimaInfo.getInt("field4"));

                              } catch (JSONException e) {
                            atualizarTextBoxes(-1,-1,-1);
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

        if (temp==-1 && pressAtm==-1 && hum==-1){
            txtTemp.setText("N/A");
            txtPressAtm.setText("N/A");
            txtHum.setText("N/A");
        }else{
            txtTemp.setText(String.valueOf(temp+" ºC"));
            txtPressAtm.setText(String.valueOf(pressAtm+" hPa"));
            txtHum.setText(String.valueOf(hum+" %"));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1){
            if (resultCode==RESULT_OK){
                SharedPreferences prefs = this.getSharedPreferences("favorito", Context.MODE_PRIVATE);
                favorito = prefs.getString("favorito","Lisboa");

                TextView txtCidade = findViewById(R.id.textCidadeName);
                txtCidade.setText(favorito);
                atualizarQualidadeAr();
            }
        }
    }

    public void onClickLocais(View view) {
        Intent it = new Intent(this, LocaisActivity.class);

        startActivityForResult(it, 1);
    }

    public void onClickSensores(View view) {
        Intent it = new Intent(this, SensoresActivity.class);

        startActivityForResult(it, 1);
    }

    public void onClickMyLoc(View view) {
        Intent it = new Intent(this, MapsActivity.class);

        startActivityForResult(it,1);
    }
}

