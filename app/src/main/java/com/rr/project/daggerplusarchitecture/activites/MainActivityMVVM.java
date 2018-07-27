package com.rr.project.daggerplusarchitecture.activites;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rr.project.daggerplusarchitecture.MyApp;
import com.rr.project.daggerplusarchitecture.R;
import com.rr.project.daggerplusarchitecture.Utils.SharedPref;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class MainActivityMVVM extends AppCompatActivity {
    @Inject
    Application application;
    @Inject
    SharedPref sharedPref;
    @Inject
    Retrofit retrofit;

    @BindView(R.id.img_sign_up_back)
    ImageView goBack;
    @BindView(R.id.txt_sign_up_header)
    TextView titleHeader;
    @BindView(R.id.txt_sign_up_subheader)
    TextView titleSubHeader;
    @BindView(R.id.container)
    FrameLayout container;

    private Application context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mvvm);

        ButterKnife.bind(this);
        ((MyApp) getApplication()).getNetComponent().inject(this);

        context = application;
    }
}
