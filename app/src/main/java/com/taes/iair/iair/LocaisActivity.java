package com.taes.iair.iair;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LocaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locais);

        SharedPreferences prefs = this.getSharedPreferences("favorito", Context.MODE_PRIVATE);
        String favorito = prefs.getString("favorito",null);
        Toast.makeText(this, favorito, Toast.LENGTH_SHORT).show();

        ArrayList<String> cidades = todasCidades();

        final ListView listaDeCidades = (ListView) findViewById(R.id.listLocais);

        //chamada da nossa implementação
        final AdapterCidades adapter = new AdapterCidades(this, cidades);
        listaDeCidades.setAdapter(adapter);



        listaDeCidades.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            switch (position) {
                case 0:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    break;
                case 1:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    break;
                case 3:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    break;
            }
            return true;
        }

        });



        listaDeCidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        callInfoLocalActivity();
                        break;
                    case 1: callInfoLocalActivity();
                        break;
                    case 2:callInfoLocalActivity();
                        break;
                    case 3:callInfoLocalActivity();
                        break;
                }

            }

        });




    }

    private ArrayList<String> todasCidades() {
        ArrayList<String> cidades = new ArrayList<>();

        cidades.add("Leiria");
        cidades.add("Lisboa");
        cidades.add("Coimbra");
        cidades.add("Porto");

        return cidades;
    }

    private void callInfoLocalActivity() {
        Intent it = new Intent(this, InfoLocalActivity.class);
        startActivity(it);
    }


}


