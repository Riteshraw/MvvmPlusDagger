package com.rr.project.daggerplusarchitecture.dagger;

import com.rr.project.daggerplusarchitecture.MyApp;
import com.rr.project.daggerplusarchitecture.activites.MainActivity;
import com.rr.project.daggerplusarchitecture.activites.MainActivityMVVM;
import com.rr.project.daggerplusarchitecture.fragments.LoginFragment;
import com.rr.project.daggerplusarchitecture.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by admin on 18-Jun-18.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(MainActivityMVVM mainActivityMVVM);
    void inject(LoginFragment loginFragment);

    UserRepository userRepository();
}

