package com.lx.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author: lixiang
 * @date: 2017/10/30 16:47
 * @description： Toast统一管理类
 */
public class ToastUtil {
    private static Toast toast;

    private ToastUtil() {
    }

    public static void show(Context paramContext, int paramInt) {
        show(paramContext, paramContext.getResources().getText(paramInt), Toast.LENGTH_SHORT);
    }

    public static void show(Context paramContext, int paramText, int paramInt) {
        show(paramContext, paramContext.getResources().getText(paramText), paramInt);
    }

    public static void show(Context paramContext, CharSequence paramCharSequence) {
        show(paramContext, paramCharSequence, Toast.LENGTH_SHORT);
    }

    public static void show(Context paramContext, CharSequence paramCharSequence, int paramInt) {
        if (toast == null) {
            toast = Toast.makeText(paramContext, paramCharSequence, paramInt);
        } else {
            toast.setText(paramCharSequence);
            toast.setDuration(paramInt);
        }
        toast.show();
    }

    public static void toastCancle() {
        if (toast != null)
            toast.cancel();
    }

}
