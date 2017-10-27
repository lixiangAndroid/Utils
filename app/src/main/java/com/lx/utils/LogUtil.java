package com.lx.utils;

import android.util.Log;

/**
 * @author: lixiang
 * @date: 2017/10/26 13:49
 * @description： log信息调试工具类
 */
public class LogUtil {
    private LogUtil() {
    }

    public static final String TAG     = "TAG";
    public static final int    VERBOSE = 1;
    public static final int    DEBUG   = 2;
    public static final int    INFO    = 3;
    public static final int    WARN    = 4;
    public static final int    ERROR   = 5;
    public static final int    NOTHING = 6;
    public static final int    LEVEL   = VERBOSE;//打印所有等级的信息
//    public static final int    LEVEL   = NOTHING;//关闭所有等级的信息

    public static void v(String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (LEVEL <= INFO) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (LEVEL <= WARN) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (LEVEL <= ERROR) {
            Log.e(TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }

}
