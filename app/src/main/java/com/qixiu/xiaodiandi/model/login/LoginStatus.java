package com.qixiu.xiaodiandi.model.login;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.qixiu.qixiu.utils.Preference;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.ui.activity.Loginactivity;

public class LoginStatus {

    public static void saveState(LoginBean bean) {
        Gson gson = new Gson();
        String str = gson.toJson(bean);
        Preference.put(ConstantString.USERBEAN, str);
        Preference.put(ConstantString.USERID, bean.getO());
    }

    public static LoginBean getUserBean() {
        String str = Preference.get(ConstantString.USERBEAN, "");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Gson gson = new Gson();
        LoginBean bean = gson.fromJson(str, LoginBean.class);
        return bean;
    }

    public static boolean isLogin() {
        String str = Preference.get(ConstantString.USERBEAN, "");
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return true;
    }

    public static void LogOut(Activity activity) {
        Preference.clearFlag(ConstantString.USERBEAN);
        activity.finish();
        Loginactivity.start(activity.getApplicationContext(), Loginactivity.class);
    }

    public static String getId(){
        LoginBean userBean = getUserBean();
        if(userBean!=null){
            return userBean.getO();
        }else {
            return null;
        }
    }
}
