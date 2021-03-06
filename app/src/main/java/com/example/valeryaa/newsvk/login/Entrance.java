package com.example.valeryaa.newsvk.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.valeryaa.newsvk.R;
import com.example.valeryaa.newsvk.db.Account;
import com.example.valeryaa.newsvk.db.AppDatabase;
import com.example.valeryaa.newsvk.news.News;

public class Entrance extends AppCompatActivity implements View.OnClickListener{
    final String LOG_DAO = "myLogs";

    public static Entrance instance;
    private AppDatabase database;

    Button btnReg, btnEntrance;
    EditText log, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        btnReg = (Button) findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);

        btnEntrance = (Button) findViewById(R.id.btnEntrance);
        btnEntrance.setOnClickListener(this);

        log = (EditText) findViewById(R.id.login);
        pass = (EditText) findViewById(R.id.password);
    }

    public void onClick(View view){
        Intent intent;
        Toast toast;
//        database = Registration.getInstance().getDatabase();

        String l = log.getText().toString();
        String p = pass.getText().toString();

        switch (view.getId()){
            case R.id.btnReg:
                intent = new Intent(this, Registration.class);
                startActivity(intent);
                Log.d(LOG_DAO, "--- регистрация ---");
                break;
            case R.id.btnEntrance:
                Account account= new Account();

                Log.d(LOG_DAO, "--- Ввод данных ---");
                if(l == account.login & p == account.password){
                    intent = new Intent(this, News.class);
                    startActivity(intent);
                    Log.d(LOG_DAO, "--- Вход ---");
                }else{
                    toast = Toast.makeText(this, "Проверьте правильность введённых данных", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 30,160);
                    toast.show();
                }
                break;
        }
    }

}
