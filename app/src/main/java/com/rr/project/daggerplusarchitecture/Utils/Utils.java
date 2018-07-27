package com.rr.project.daggerplusarchitecture.Utils;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by admin on 18-Jun-18.
 */

public class Utils {

    public static String getText(EditText c) {
        if (c.getText() != null)
            return c.getText().toString().trim();
        else
            return "";
    }


    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
