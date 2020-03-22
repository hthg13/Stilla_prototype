package com.example.helga.stilla.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.helga.stilla.Repository.LoginRepository;
import com.example.helga.stilla.Room.Entity.LoginTable;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository repository;
    private LiveData<List<LoginTable>> getAllData;
    private LiveData<Integer> numEntries;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        repository = new LoginRepository(application);
        getAllData = repository.getAllData();
        numEntries = repository.numEntries();
    }

    public void insert(LoginTable data) {
        repository.insertData(data);
    }

    public LiveData<List<LoginTable>> getGetAllData() {
        return getAllData;
    }

    public LiveData<Integer> numEntries() {return numEntries;}

}

