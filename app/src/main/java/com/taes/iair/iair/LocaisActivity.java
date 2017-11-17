package com.taes.iair.iair;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LocaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locais);

        SharedPreferences prefs = this.getSharedPreferences(
                "favorito", Context.MODE_PRIVATE);
        String favorito = prefs.getString("favorito",null);
        Toast.makeText(this, favorito, Toast.LENGTH_SHORT).show();
    }


}
