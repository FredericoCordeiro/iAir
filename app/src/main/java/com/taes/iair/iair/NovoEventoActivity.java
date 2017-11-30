package com.taes.iair.iair;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NovoEventoActivity extends AppCompatActivity {

    String username;
    SharedPreferences prefs;
    String nome;

    EditText editTextTitulo;
    EditText editTextCategoria;
    EditText editTextDescricao;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_evento);

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextCategoria = findViewById(R.id.editTextCategoria);
        editTextDescricao = findViewById(R.id.editTextDescricao);

        Intent it = getIntent();
        nome = it.getStringExtra("nome");

         prefs = this.getSharedPreferences("username", Context.MODE_PRIVATE);
    }

    public void onClickPubEvent(View view) {
        username = prefs.getString("username", "N/A");

        if (username.isEmpty() || username=="N/A") {
            Toast.makeText(NovoEventoActivity.this, "Tem de definir um Username no Menu de Opções para poder publicar!", Toast.LENGTH_SHORT).show();
        } else {
            inserirEvento(nome);
            finish();
            Intent it = new Intent(this, InfoLocalActivity.class);
            startActivity(it);
        }

    }

    public void inserirEvento(String nome){


        RequestQueue queue = Volley.newRequestQueue(NovoEventoActivity.this);

        String url=null;

        switch (nome){
            case "Aveiro":
                url="https://api.thingspeak.com/update?api_key=5EVM52CHRIOSK9EG";
                break;
            case "Beja":
                url="https://api.thingspeak.com/update?api_key=Z7LP0GQMGRT5KLH9";
                break;
            case "Braga":
                url="https://api.thingspeak.com/update?api_key=BWTU49J90RZD50ZN";
                break;
            case "Bragança":
                url="https://api.thingspeak.com/update?api_key=3WALGRWOGS0RJZBH";
                break;
            case "Castelo Branco":
                url="https://api.thingspeak.com/update?api_key=EZKWLGCJVIS0F2EQ";
                break;
            case "Coimbra":
                url="https://api.thingspeak.com/update?api_key=SIMYEQFGRJJWRFBZ";
                break;
            case "Évora":
                url="https://api.thingspeak.com/update?api_key=IZIBAVEQADM5GYD6";
                break;
            case "Faro":
                url="https://api.thingspeak.com/update?api_key=RLQD4SATEO4TINF1";
                break;
            case "Guarda":
                url="https://api.thingspeak.com/update?api_key=O1R45A0WXTZCEVGM";
                break;
            case "Leiria":
                url="https://api.thingspeak.com/update?api_key=759J3YYLTXYFE0T4";
                break;
            case "Lisboa":
                url="https://api.thingspeak.com/update?api_key=1W3H1UBOXRQN09B7";
                break;
            case "Portalegre":
                url="https://api.thingspeak.com/update?api_key=KEA1Y22SU6X9Z1ZI";
                break;
            case "Porto":
                url="https://api.thingspeak.com/update?api_key=8HM1XLP1RBBH075Q";
                break;
            case "Santarém":
                url="https://api.thingspeak.com/update?api_key=GUKTZVTH966YCUZI";
                break;
            case "Setúbal":
                url="https://api.thingspeak.com/update?api_key=CBH0YDM3CP5WYMI7";
                break;
            case "Viana do Castelo":
                url="https://api.thingspeak.com/update?api_key=Q090MSELNX6DBHC0";
                break;
            case "Vila Real":
                url="https://api.thingspeak.com/update?api_key=AQ6O39CCD5NG8TIX";
                break;
            case "Viseu":
                url="https://api.thingspeak.com/update?api_key=6FHPND6E3OZFF9D4";
                break;
            default:
                Toast.makeText(this, "Erro ao identificar o Local!", Toast.LENGTH_SHORT).show();
                break;
        }




        url +=" &field1=" +username+
                "&field2="+editTextTitulo.getText().toString()
                +"&field3="+editTextCategoria.getText().toString()
                +"&field4="+editTextDescricao.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(NovoEventoActivity.this, "Publicado com sucesso! Nº registo : "+response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NovoEventoActivity.this, "Erro a inserir", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }
}
