package com.rr.project.daggerplusarchitecture.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.rr.project.daggerplusarchitecture.Utils.Utils;
import com.rr.project.daggerplusarchitecture.room.AppDatabase;
import com.rr.project.daggerplusarchitecture.room.User;
import com.rr.project.daggerplusarchitecture.room.UserDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class UserRepository {

    private Application application;
    private LiveData<List<User>> allUsers;
    private UserDao mUserDao;


    @Inject
    public UserRepository(Application application) {
        this.application = application;
        AppDatabase db = AppDatabase.getDbInstance(application);
        mUserDao = db.userDao();
        allUsers = mUserDao.getAll();
    }

    /*public UserRepository() {
        if(application!=null)
            Utils.showToast(application,"app null");
    }*/

    public LiveData<List<User>> getAllUser() {
        return allUsers;
    }

    public void insertUser(User user) {
        new insertAsyncTask(mUserDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insertAll(params[0]);
            return null;
        }
    }
}
