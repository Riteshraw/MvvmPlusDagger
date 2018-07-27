package com.rr.project.daggerplusarchitecture.Utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by admin on 19-Jun-18.
 */

public class Validation {

    private static boolean isValidMobile(String mobile) {
        String pattern = "[789][0-9]{9}";
        return mobile.matches(pattern);
    }

    private static void requestFocus(View view, Activity activity) {
        if (view.requestFocus()) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public static boolean validateMobileNo(EditText input_mobile, TextInputLayout input_layout_mobile, Activity activity) {
        String mobile_no = Utils.getText(input_mobile);

        if (mobile_no.isEmpty() || !isValidMobile(mobile_no)) {
            input_layout_mobile.setError("Enter valid mobile no");
            requestFocus(input_layout_mobile, activity);
            return false;
        } else
            input_layout_mobile.setErrorEnabled(false);

        return true;
    }

    public static boolean validateCheckBox(CheckBox checkbox_tc, Context context) {
        if (!checkbox_tc.isChecked()) {
            Utils.showToast(context, "Please check terms and conditions");
            return false;
        }
        return true;
    }

    public static boolean validateOTP(EditText input_otp, TextInputLayout input_layout_otp, Activity activity) {
        String otp = Utils.getText(input_otp);

        if (otp.isEmpty() || otp.length() < 6) {
            input_layout_otp.setError("Enter valid OTP");
            requestFocus(input_layout_otp, activity);
            return false;
        } else
            input_layout_otp.setErrorEnabled(false);

        return true;
    }

    public static boolean validatePassword(EditText input_password, TextInputLayout input_layout_password, Activity activity) {
        String password = Utils.getText(input_password);

        if (password.isEmpty() || password.length() < 6) {
            input_layout_password.setError("Enter valid password");
            requestFocus(input_layout_password, activity);
            return false;
        } else
            input_layout_password.setErrorEnabled(false);

        return true;
    }
}
