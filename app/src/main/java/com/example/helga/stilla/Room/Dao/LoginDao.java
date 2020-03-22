package com.example.helga.stilla.Room.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.helga.stilla.Room.Entity.LoginTable;

import java.util.List;

@Dao
public interface LoginDao {

    @Insert
    void insertDetails(LoginTable data);

    @Query("select * from LoginDetails")
    LiveData<List<LoginTable>> getDetails();

    @Query("delete from LoginDetails")
    void deleteAllData();

    @Query("select count(id) from LoginDetails")
    LiveData<Integer> numEntries();

}
