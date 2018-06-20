package com.example.valeryaa.newsvk;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity (indices = {@Index(value = "login", unique = true) , @Index(value = "password")})
public class Account {
    @PrimaryKey (autoGenerate = true)
    public long id;
    public String login;
    public String password;

}
