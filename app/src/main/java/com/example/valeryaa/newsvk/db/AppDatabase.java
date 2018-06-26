package com.example.valeryaa.newsvk.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Account.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccountDao accountDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database.db")
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
