package com.rr.project.daggerplusarchitecture.services;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.rr.project.daggerplusarchitecture.Utils.Constants;
import com.rr.project.daggerplusarchitecture.room.AppDatabase;
import com.rr.project.daggerplusarchitecture.room.User;

public class DbAsync extends AsyncTask<String, Void, Long> {

    private final DbCompleteListener dbCompleteListener;
    private final User user;
    private final AppDatabase db;
    private ProgressBar progressBar;
    private String mTitle;

    public DbAsync(AppDatabase db, User user, /*ProgressBar progressBar,*/ DbCompleteListener dbCompleteListener) {
        this.db = db;
        this.user = user;
//        this.progressBar = progressBar;
        this.dbCompleteListener = dbCompleteListener;
    }

    @Override
    protected Long doInBackground(String... strings) {
        Long result = Long.valueOf(0);
        switch (strings[0]) {
            case Constants.INSERT:
                result = db.userDao().insertAll(user);
                break;
            case Constants.UPDATE:
                result = db.userDao().insertAll(user);
                break;
            case Constants.DELETE:
                result = db.userDao().insertAll(user);
                break;
        }
        return result;
    }

    @Override
    protected void onPostExecute(Long result) {
        super.onPostExecute(result);
        dbCompleteListener.onComplete(result);
    }
}
