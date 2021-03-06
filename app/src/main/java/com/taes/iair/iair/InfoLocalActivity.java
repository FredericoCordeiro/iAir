package com.taes.iair.iair;

import android.content.Intent;
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

public class InfoLocalActivity extends AppCompatActivity {

    String nome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_local);

        Intent it = getIntent();
        nome = it.getStringExtra("nome");
        this.setTitle(nome);

        atualizarQualidadeAr();
        atualizarEventos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_info_local, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.item_rtc){
            setResult(RESULT_OK);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void atualizarEventos() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url=null;
        switch (nome){
            case "Aveiro":
                url="https://api.thingspeak.com/channels/375693/feeds.json?results=1";
                break;
            case "Beja":
                url="https://api.thingspeak.com/channels/375694/feeds.json?results=1";
                break;
            case "Braga":
                url="https://api.thingspeak.com/channels/375700/feeds.json?results=1";
                break;
            case "Bragança":
                url="https://api.thingspeak.com/channels/375701/feeds.json?results=1";
                break;
            case "Castelo Branco":
                url="https://api.thingspeak.com/channels/375702/feeds.json?results=1";
                break;
            case "Coimbra":
                url="https://api.thingspeak.com/channels/375703/feeds.json?results=1";
                break;
            case "Évora":
                url="https://api.thingspeak.com/channels/375704/feeds.json?results=1";
                break;
            case "Faro":
                url="https://api.thingspeak.com/channels/375705/feeds.json?results=1";
                break;
            case "Guarda":
                url="https://api.thingspeak.com/channels/375706/feeds.json?results=1";
                break;
            case "Leiria":
                url="https://api.thingspeak.com/channels/375707/feeds.json?results=1";
                break;
            case "Lisboa":
                url="https://api.thingspeak.com/channels/375708/feeds.json?results=1";
                break;
            case "Portalegre":
                url="https://api.thingspeak.com/channels/375710/feeds.json?results=1";
                break;
            case "Porto":
                url="https://api.thingspeak.com/channels/375711/feeds.json?results=1";
                break;
            case "Santarém":
                url="https://api.thingspeak.com/channels/375712/feeds.json?results=1";
                break;
            case "Setúbal":
                url="https://api.thingspeak.com/channels/375713/feeds.json?results=1";
                break;
            case "Viana do Castelo":
                url="https://api.thingspeak.com/channels/375714/feeds.json?results=1";
                break;
            case "Vila Real":
                url="https://api.thingspeak.com/channels/375715/feeds.json?results=1";
                break;
            case "Viseu":
                url="https://api.thingspeak.com/channels/375716/feeds.json?results=1";
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


                            atualizarTextBoxesEventos(ultimaInfo.getString("field2"),
                                    ultimaInfo.getString("field3"),
                                    ultimaInfo.getString("field4"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InfoLocalActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }

    private void atualizarTextBoxesEventos(String titulo, String categoria, String descricao) {
        final TextView txtTitulo = findViewById(R.id.txtTitulo);
        final TextView txtCategoria = findViewById(R.id.txtCategoria);
        final TextView txtDescricao = findViewById(R.id.txtDescricao);

        txtTitulo.setText(titulo);
        txtCategoria.setText(categoria);
        txtDescricao.setText(descricao);

    }

    public void atualizarQualidadeAr(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url=null;
        switch (nome){
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
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InfoLocalActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }

    public void atualizarTextBoxes( int temp,int pressAtm,int hum){
        final TextView txtHum = findViewById(R.id.textViewHumRel1);
        final TextView txtPressAtm = findViewById(R.id.textViewPressAtm1);
        final TextView txtTemp = findViewById(R.id.textViewTempAr1);

        txtTemp.setText(String.valueOf(temp+" ºC"));
        txtPressAtm.setText(String.valueOf(pressAtm+" hPa"));
        txtHum.setText(String.valueOf(hum+" %"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==3){
            if (resultCode==RESULT_OK) {
                atualizarEventos();
            }
        }
    }

    public void onClickAddEvento(View view) {
        Intent it = new Intent(this, NovoEventoActivity.class);
        it.putExtra("nome", nome);
        startActivityForResult(it, 3);

    }
}
