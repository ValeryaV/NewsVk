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

    Button btnReg, btnEntrance;
    EditText log, pass;

    AppDatabase db;
    AccountDao accountDao;

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
    }

    public void onClick(View view){
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        AccountDao accountDao = db.accountDao();

        String l = log.getText().toString();
        String p = pass.getText().toString();

        switch (view.getId()){
            case R.id.btnReg:
                Account account= new Account();
                Log.d(LOG_DAO, "--- Добавление данных ---");
                account.login = "w3w3";
                account.password = "s5s5s";
                long idRow = accountDao.insert(account);
                Log.d(LOG_DAO, "!!! Данные добавлены id = !!!"+ idRow);
                break;
            case R.id.btnEntrance:
                Intent intent = new Intent(this, Entrance.class);
                startActivity(intent);
                break;
        }
    }
}

/*
public class App extends Application {
    public static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "db").build();
    }

    public static App getInstance(){
        return instance;
    }

    public AppDatabase getDatabase(){
        return database;
    }
}
 */

