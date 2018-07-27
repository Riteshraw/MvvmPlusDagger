package com.rr.project.daggerplusarchitecture.activites;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rr.project.daggerplusarchitecture.MyApp;
import com.rr.project.daggerplusarchitecture.R;
import com.rr.project.daggerplusarchitecture.Utils.Constants;
import com.rr.project.daggerplusarchitecture.Utils.SharedPref;
import com.rr.project.daggerplusarchitecture.Utils.Utils;
import com.rr.project.daggerplusarchitecture.fragments.LoginFragment;
import com.rr.project.daggerplusarchitecture.repository.UserRepository;
import com.rr.project.daggerplusarchitecture.room.AppDatabase;
import com.rr.project.daggerplusarchitecture.room.User;
import com.rr.project.daggerplusarchitecture.services.DbAsync;
import com.rr.project.daggerplusarchitecture.services.DbCompleteListener;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginSubmitListener {
    @Inject
    SharedPref sharedPref;
    @Inject
    Retrofit retrofit;
    @Inject
    UserRepository userRepository;

    @BindView(R.id.img_sign_up_back)
    ImageView goBack;
    @BindView(R.id.txt_sign_up_header)
    TextView titleHeader;
    @BindView(R.id.txt_sign_up_subheader)
    TextView titleSubHeader;
    @BindView(R.id.container)
    FrameLayout container;

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Context context;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((MyApp) getApplication()).getNetComponent().inject(this);
        context = this;

//        UserRepository userRepository = new UserRepository();
        userRepository.getAllUser();

        //How to use
//        db = Room.databaseBuilder(this, AppDatabase.class, Constants.DB_NAME).build();
        db = AppDatabase.getDbInstance(context);

        //        long id = db.userDao().insertAll(user);

        if (sharedPref != null)
            sharedPref.saveString(Constants.USER_NAME, "Ritesh");

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        addFragment(new LoginFragment(), "LoginFragment");

        android.arch.lifecycle.Observer<List<User>> listObserver = new android.arch.lifecycle.Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                if (users.size() > 0) {
                    titleSubHeader.setText(users.get(users.size() - 1).getUserEmail());
                    titleHeader.setText("" +
                            users.get(users.size() - 1).getUserID());
                }
                Utils.showToast(context, "Users fetched : " + users.size());
            }
        };

        db.userDao().getAll().observe(this, listObserver);

    }

    private void addFragment(Fragment fragment, String tag) {
        ft.replace(R.id.container, fragment, "LoginFragment");
        ft.addToBackStack("LoginFragment");
        ft.commit();
    }

    @Override
    public void onLoginSubmit(String mobileNo) {
//        Utils.showToast(context, "Hit : " + mobileNo);
//        titleHeader.setText(R.string.Otp);
//        titleSubHeader.setText(R.string.Otp_text);
        insertUser();

    }

    public void insertUser() {
        User user = new User();
//        user.setUserID(1);
        user.setFirstName("test");
        user.setLastName("rawat");
        user.setUserEmail("test@gmail.com");

        new DbAsync(db, user, new DbCompleteListener() {
            @Override
            public void onComplete(Long result) {
                Utils.showToast(context, "" + result);
            }
        }).execute(Constants.INSERT);
    }
}
