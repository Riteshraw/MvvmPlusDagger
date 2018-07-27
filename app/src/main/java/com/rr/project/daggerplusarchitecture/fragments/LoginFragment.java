package com.rr.project.daggerplusarchitecture.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;


import com.rr.project.daggerplusarchitecture.MyApp;
import com.rr.project.daggerplusarchitecture.R;
import com.rr.project.daggerplusarchitecture.Utils.SharedPref;
import com.rr.project.daggerplusarchitecture.Utils.Utils;
import com.rr.project.daggerplusarchitecture.Utils.Validation;
import com.rr.project.daggerplusarchitecture.activites.MainActivity;
import com.rr.project.daggerplusarchitecture.viewModels.LoginViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 19-Jun-18.
 */

public class LoginFragment extends Fragment {
    @Inject
    SharedPref sharedPref;

    @BindView(R.id.input_mobile)
    EditText input_mobile;
    @BindView(R.id.input_layout_mobile)
    TextInputLayout input_layout_mobile;
    @BindView(R.id.checkbox_tc)
    CheckBox checkbox_tc;

    private LoginSubmitListener loginSubmitListener;
    private Context context;
    private static final String UID_KEY = "uid";
    private LoginViewModel loginViewModel;

    @Override
    public void onAttach(Context context) {
        ((MyApp) ((MainActivity) context).getApplication()).getNetComponent().inject(this);
        super.onAttach(context);
        this.context = context;

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            loginSubmitListener = (LoginSubmitListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnHeadlineSelectedListener");
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        String userId = getArguments().getString(UID_KEY);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
//        loginViewModel.init(userId);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_sign_up_submit)
    void onSubmit(View view) {
//        if (!Validation.validateMobileNo(input_mobile, input_layout_mobile, (MainActivity) context))
//            return;
//        if (!Validation.validateCheckBox(checkbox_tc, context))

            loginSubmitListener.onLoginSubmit(Utils.getText(input_mobile));
    }

    public interface LoginSubmitListener {
        void onLoginSubmit(String mobileNo);
    }

}
