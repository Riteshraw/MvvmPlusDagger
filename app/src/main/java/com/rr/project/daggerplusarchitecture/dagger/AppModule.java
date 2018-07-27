package com.rr.project.daggerplusarchitecture.dagger;

import android.app.Application;
import android.content.Context;

import com.rr.project.daggerplusarchitecture.Utils.SharedPref;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 18-Jun-18.
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application){
        this.mApplication = application;
    }

    @Singleton
    @Provides
    Application providesApplicationContext(){
        return mApplication;
    }

    @Singleton
    @Provides
    SharedPref providesSharedPref(Application context){
        return new SharedPref(context);
    }
}
