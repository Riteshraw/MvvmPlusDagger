package com.rr.project.daggerplusarchitecture.viewModels;

import android.arch.lifecycle.ViewModel;

import com.rr.project.daggerplusarchitecture.dao.User;

/**
 * Created by admin on 19-Jun-18.
 */

public class LoginViewModel extends ViewModel {
    String userId;
    private User user;

    public void init(String userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public boolean saveUserInDb(User user) {
        return true;
    }



}
