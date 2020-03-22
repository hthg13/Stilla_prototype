package com.example.helga.stilla.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.helga.stilla.Room.Dao.LoginDao;
import com.example.helga.stilla.Room.Database.LoginDatabase;
import com.example.helga.stilla.Room.Entity.LoginTable;

import java.util.List;

public class LoginRepository {

    private LoginDao loginDao;
    private LiveData<List<LoginTable>> allData;
    private LiveData<Integer> numEntries;

    public LoginRepository(Application application) {
        LoginDatabase db = LoginDatabase.getDatabase(application);
        loginDao = db.loginDoa();
        allData = loginDao.getDetails();
        numEntries = loginDao.numEntries();
    }

    public LiveData<Integer> numEntries(){
        return numEntries;
    }

    public void deleteData() {
        loginDao.deleteAllData();
    }

    public LiveData<List<LoginTable>> getAllData() {
        return allData;
    }

    public void insertData(LoginTable data) {
        new LoginInsertion(loginDao).execute(data);
    }

    private static class LoginInsertion extends AsyncTask<LoginTable, Void, Void> {
        private LoginDao loginDao;

        private LoginInsertion(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(LoginTable... data) {
            loginDao.deleteAllData();
            loginDao.insertDetails(data[0]);
            return null;
        }

    }

}
