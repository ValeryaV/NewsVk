package com.example.valeryaa.newsvk.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AccountDao {
    @Query("select * from account")
    List<Account> getAll();

    @Query("select * from account where id = :id")
    Account getById(long id);

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    long insert (Account account);

}
