package com.rr.project.daggerplusarchitecture.dagger;

import android.app.Application;

import com.rr.project.daggerplusarchitecture.Utils.SharedPref;
import com.rr.project.daggerplusarchitecture.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 18-Jun-18.
 */

@Module(includes = AppModule.class)
public class NetModule {
    String mbaseUrl;

    public NetModule(String baseUrl) {
        this.mbaseUrl = baseUrl;
    }

//    @Singleton
//    @Provides
//    SharedPref providesSharedPref(Application applica/tion) {
//        return new SharedPref(application);
//    }

    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mbaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Provides
    UserRepository userRepository(Application application){
        return new UserRepository(application);
    }

}
