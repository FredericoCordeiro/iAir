package com.taes.iair.iair;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UsernameActivity extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        SharedPreferences prefs = this.getSharedPreferences("username", Context.MODE_PRIVATE);

        String username = prefs.getString("username","N/A");



        editText = (EditText) findViewById(R.id.editTextUsername);
        editText.setText(username);
        editText.requestFocus();
    }

    public void onClickUsername(View view) {
        SharedPreferences prefs = this.getSharedPreferences("username", Context.MODE_PRIVATE);
        prefs.edit().putString("username",editText.getText().toString()).apply();
        finish();

    }
}
