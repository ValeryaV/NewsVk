package com.example.valeryaa.newsvk;

import android.arch.persistence.room.Room;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    final String LOG_DAO = "myLogs";

    public static Registration instance;
    private AppDatabase database;

    Button btnReg, btnEntrance;
    EditText log, pass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnReg = (Button) findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);

        btnEntrance = (Button) findViewById(R.id.btnEntrance);
        btnEntrance.setOnClickListener(this);

        log = (EditText) findViewById(R.id.login);
        pass = (EditText) findViewById(R.id.password);

        instance = this;
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database").build();
    }

    public static Registration getInstance(){
        return instance;
    }

    public AppDatabase getDatabase(){
        return database;
    }

    public void onClick(View view){
        AppDatabase db = Registration.getInstance().getDatabase();

        String l = log.getText().toString();
        String p = pass.getText().toString();

        AccountDao accountDao = db.accountDao();

        switch (view.getId()){
            case R.id.btnReg:
                Account account= new Account();
                Log.d(LOG_DAO, "--- Добавление данных ---");
                account.uid = 5;
                account.login = "bbb";
                account.password = "aa";
                accountDao.insert(account);
                Log.d(LOG_DAO, "!!! Данные добавлены !!!");
                break;
            case R.id.btnEntrance:
                Intent intent = new Intent(this, Entrance.class);
                startActivity(intent);
                break;
        }

        db.close();
    }
}

