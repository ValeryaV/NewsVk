package com.example.valeryaa.newsvk.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (indices = {@Index(value = "login", unique = true) , @Index(value = "password")})
public class Account {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public long id
    public String login;
    public String password;

}
