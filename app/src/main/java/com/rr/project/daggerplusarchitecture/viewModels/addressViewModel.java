package com.rr.project.daggerplusarchitecture.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rr.project.daggerplusarchitecture.Utils.Utils;
import com.rr.project.daggerplusarchitecture.room.AppDatabase;
import com.rr.project.daggerplusarchitecture.room.User;
import com.rr.project.daggerplusarchitecture.room.UserAddress;

import java.util.ArrayList;
import java.util.List;

public class addressViewModel extends AndroidViewModel {
    private final Application mapplication;
    private MutableLiveData<UserAddress> address;
    private MutableLiveData<ArrayList<UserAddress>> addresList;

    public addressViewModel(@NonNull Application application) {
        super(application);
        mapplication = application;
    }

//    AppDatabase db = AppDatabase.getDbInstance(context);


    public MutableLiveData<ArrayList<UserAddress>> getAllAddress() {
        if (addresList == null) {
            addresList = new MutableLiveData<ArrayList<UserAddress>>();
            loadAddress();
        }
        return addresList;
    }

    private void loadAddress() {
        AppDatabase db = AppDatabase.getDbInstance(mapplication);
        Observer<MutableLiveData<ArrayList<UserAddress>>> addressListObserver = new Observer<MutableLiveData<ArrayList<UserAddress>>>() {
            @Override
            public void onChanged(@Nullable MutableLiveData<ArrayList<UserAddress>> listMutableLiveData) {
                addresList = listMutableLiveData;
            }
        };

//        db.addressDao().getAll().observe(mapplication, addressListObserver);
    }
}
