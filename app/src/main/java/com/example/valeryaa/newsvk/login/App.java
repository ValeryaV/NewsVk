package com.example.valeryaa.newsvk.login;

import android.arch.persistence.room.Room;

import com.example.valeryaa.newsvk.db.AppDatabase;

public class App extends android.app.Application {
    public static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database").build();
    }

    public static App getInstance(){
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
