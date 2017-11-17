package com.taes.iair.iair;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LocaisActivity extends AppCompatActivity {

    List<String> cidades;
    ArrayAdapter<String> adaptador;
    ListView listCidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locais);

        SharedPreferences prefs = this.getSharedPreferences(
                "favorito", Context.MODE_PRIVATE);
        String favorito = prefs.getString("favorito",null);
        Toast.makeText(this, favorito, Toast.LENGTH_SHORT).show();

        listCidades = (ListView) findViewById(R.id.listLocais);

        cidades = new ArrayList<String>();

        cidades.add("Leiria");
        cidades.add("Lisboa");
        cidades.add("Coimbra");
        cidades.add("Porto");

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, cidades);
        listCidades.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listCidades.setAdapter(adaptador);


        listCidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Object item = parent.getItemAtPosition(position);
                        callInfoLocalActivity();

                        if (item != null) {
                            Toast.makeText(LocaisActivity.this, item.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(LocaisActivity.this, "Selected",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 1: //callInfoLocalActivity();
                        break;
                    case 2://callInfoLocalActivity();
                        break;
                    case 3: //callInfoLocalActivity();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }



        });

    }


    private void callInfoLocalActivity() {
        Intent it = new Intent(this, InfoLocalActivity.class);
        startActivity(it);
    }



}


