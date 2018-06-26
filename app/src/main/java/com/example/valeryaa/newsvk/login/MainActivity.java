package com.example.valeryaa.newsvk.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.valeryaa.newsvk.R;

public class MainActivity extends AppCompatActivity {
    final String LOG_DAO = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onEntrance(View view){
        Intent intent = new Intent(this, Entrance.class);
        startActivity(intent);
        Log.d(LOG_DAO, "--- Вход ---");
    }

    public void onRegistration (View view){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
        Log.d(LOG_DAO, "--- регистрация ---");
    }

}
