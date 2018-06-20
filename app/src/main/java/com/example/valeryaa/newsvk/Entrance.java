package com.example.valeryaa.newsvk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Entrance extends AppCompatActivity implements View.OnClickListener{
    final String LOG_DAO = "myLogs";

    Button btnReg, btnEntrance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        btnReg = (Button) findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);

        btnEntrance = (Button) findViewById(R.id.btnEntrance);
        btnEntrance.setOnClickListener(this);
    }

    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.btnReg:
                intent = new Intent(this, Registration.class);
                startActivity(intent);
                Log.d(LOG_DAO, "--- регистрация ---");
                break;
            case R.id.btnEntrance:
                intent = new Intent(this, Entrance.class);
                startActivity(intent);
                Log.d(LOG_DAO, "--- Вход ---");
                break;
        }
    }

}
