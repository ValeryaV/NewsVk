package com.example.valeryaa.newsvk;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Account {
    @PrimaryKey (autoGenerate = true)
    public long uid;
    public String login;
    public String password;

}
