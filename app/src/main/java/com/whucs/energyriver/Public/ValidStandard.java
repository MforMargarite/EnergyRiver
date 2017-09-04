package com.whucs.energyriver.Public;


import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.whucs.energyriver.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidStandard {
    public static boolean isMobile(String mobile){
        Pattern pattern = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}");
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password){
        if(password.trim().length()<6)
            return false;
        else
            return true;
    }

    public static boolean isIDCard(String idcard){
        Pattern pattern = Pattern.compile("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X|x)");
        Matcher matcher = pattern.matcher(idcard);
        return matcher.matches();
    }

    public static boolean isValidContent(String content){
        if(!content.trim().equals(""))
            return true;
        return false;
    }

    public static boolean isLegal(EditText et) {
        String result = et.getText().toString();
        if (!result.trim().equals(""))
            return true;
        else {
            et.requestFocus();
            et.requestFocusFromTouch();
            return false;
        }
    }
}
