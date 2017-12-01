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


        final SharedPreferences prefs = this.getSharedPreferences("favorito", Context.MODE_PRIVATE);


        ArrayList<String> cidades = todasCidades();



        String favorito = prefs.getString("favorito",null);
        final ListView listaDeCidades = (ListView) findViewById(R.id.listLocais);

        //chamada da nossa implementação
        final AdapterCidades adapter = new AdapterCidades(this, cidades);
        listaDeCidades.setAdapter(adapter);


        if (cidades.contains(favorito)){
            int a = cidades.indexOf(favorito);
            adapter.setSelectedIndex(a);
            adapter.notifyDataSetChanged();
        }



        listaDeCidades.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            switch (position) {
                case 0:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Aveiro").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 1:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Beja").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 2:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Braga").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 3:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Bragança").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 4:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Castelo Branco").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 5:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Coimbra").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 6:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Évora").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 7:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Faro").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 8:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Guarda").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 9:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Leiria").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 10:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Lisboa").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 11:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Portalegre").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 12:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Porto").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 13:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Santarém").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 14:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Setúbal").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 15:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Viana do Castelo").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 16:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Vila Real").apply();
                    setResult(RESULT_OK);
                    finish();
                    break;
                case 17:
                    adapter.setSelectedIndex(position);
                    adapter.notifyDataSetChanged();
                    prefs.edit().putString("favorito", "Viseu").apply();
                    setResult(RESULT_OK);
                    finish();
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
                        callInfoLocalActivity("Aveiro");
                        break;
                    case 1: callInfoLocalActivity("Beja");
                        break;
                    case 2:callInfoLocalActivity("Braga");
                        break;
                    case 3:callInfoLocalActivity("Bragança");
                        break;
                    case 4:
                        callInfoLocalActivity("Castelo Branco");
                        break;
                    case 5: callInfoLocalActivity("Coimbra");
                        break;
                    case 6:callInfoLocalActivity("Évora");
                        break;
                    case 7:callInfoLocalActivity("Faro");
                        break;
                    case 8:
                        callInfoLocalActivity("Guarda");
                        break;
                    case 9: callInfoLocalActivity("Leiria");
                        break;
                    case 10:callInfoLocalActivity("Lisboa");
                        break;
                    case 11:callInfoLocalActivity("Portalegre");
                        break;
                    case 12:
                        callInfoLocalActivity("Porto");
                        break;
                    case 13: callInfoLocalActivity("Santarém");
                        break;
                    case 14:callInfoLocalActivity("Setúbal");
                        break;
                    case 15:callInfoLocalActivity("Viana do Castelo");
                        break;
                    case 16:
                        callInfoLocalActivity("Vila Real");
                        break;
                    case 17: callInfoLocalActivity("Viseu");
                        break;
                }

            }

        });
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

    private ArrayList<String> todasCidades() {
        ArrayList<String> cidades = new ArrayList<>();

        cidades.add("Aveiro");
        cidades.add("Beja");
        cidades.add("Braga");
        cidades.add("Bragança");
        cidades.add("Castelo Branco");
        cidades.add("Coimbra");
        cidades.add("Évora");
        cidades.add("Faro");
        cidades.add("Guarda");
        cidades.add("Leiria");
        cidades.add("Lisboa");
        cidades.add("Portalegre");
        cidades.add("Porto");
        cidades.add("Santarém");
        cidades.add("Setúbal");
        cidades.add("Viana do Castelo");
        cidades.add("Vila Real");
        cidades.add("Viseu");

        return cidades;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==2){
            if (resultCode==RESULT_OK) {

            }
        }
    }

    private void callInfoLocalActivity(String name) {
        Intent it = new Intent(this, InfoLocalActivity.class);
        it.putExtra("nome", name);
        startActivityForResult(it, 2 );
    }


}


