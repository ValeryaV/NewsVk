package com.example.valeryaa.newsvk.login;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.valeryaa.newsvk.R;
import com.example.valeryaa.newsvk.db.Account;
import com.example.valeryaa.newsvk.db.AccountDao;
import com.example.valeryaa.newsvk.db.AppDatabase;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    final String LOG_DAO = "myLogs";

    AppDatabase db;

    Button btnReg, btnEntrance;
    EditText log, pass;

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

        //AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database").build();

    }

    public void onClick(View view) {
        db = App.getInstance().getDatabase();
        AccountDao accountDao = db.accountDao();

        String l = log.getText().toString();
        String p = pass.getText().toString();

        switch (view.getId()) {
            case R.id.btnReg:
                Account account = new Account();
                Log.d(LOG_DAO, "--- Добавление данных ---");
                account.login = "adfdf";
                account.password = "1qwE3-";
                long idRow = accountDao.insert(account);
                Log.d(LOG_DAO, "!!! Данные добавлены id = !!!" + idRow);
                break;
            case R.id.btnEntrance:
                //Intent intent = new Intent(this, Entrance.class);
                //startActivity(intent);
                break;
        }
    }
}


