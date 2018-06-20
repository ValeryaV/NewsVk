package com.example.valeryaa.newsvk;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AccountDao {
    @Query("select * from account")
    List<Account> getAll();

    @Query("select * from account where uid = :id")
    Account getById(long id);

    @Insert
    void insert (Account account);

    @Delete
    void delete(Account account);
}
