package com.rr.project.daggerplusarchitecture;

import android.app.Application;

import com.rr.project.daggerplusarchitecture.dagger.AppComponent;
import com.rr.project.daggerplusarchitecture.dagger.AppModule;
import com.rr.project.daggerplusarchitecture.dagger.DaggerAppComponent;
import com.rr.project.daggerplusarchitecture.dagger.NetModule;

/**
 * Created by admin on 18-Jun-18.
 */

public class MyApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        appComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://www.gsmarena.com/"))
                .build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mNetComponent = com.codepath.dagger.components.DaggerAppComponent.create();
    }

    public AppComponent getNetComponent() {
        return appComponent;
    }
}
