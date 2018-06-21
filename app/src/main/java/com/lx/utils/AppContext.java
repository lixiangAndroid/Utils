package com.lx.utils;

import android.app.Application;
import android.content.Context;

/**
 * @author: lixiang
 * @date: 2018/5/14 10:11
 * @description： com.lanyang.delivery_locker
 */
public class AppContext extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }


    public static Context getAppContext() {
        return mContext;
    }
}
