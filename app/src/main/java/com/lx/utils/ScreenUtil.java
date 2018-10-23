package com.lx.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * @author: lixiang
 * @date: 2017/10/27 9:45
 * @description： 屏幕相关辅助类, 像素相关，获取Android屏幕宽度，dp跟px互转
 */
public class ScreenUtil {
    private ScreenUtil() {
    }

    /**
     * 获得字体高度
     *
     * @param textPaint Paint
     * @return 屏幕高度
     */

    public static float getTextHeight(Paint textPaint) {
        return -textPaint.ascent() - textPaint.descent();
    }

    /**
     * 获得屏幕高度
     *
     * @param context context
     * @return 屏幕高度
     */
    public static int getScreenWidth(Context context) {
        if (context == null) return -1;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context context
     * @return 屏幕宽度
     */
    public static int getScreenHeight(Context context) {
        if (context == null) return -1;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context context
     * @return 状态栏的高度
     */
    public static int getStatusHeight(Context context) {
        if (context == null) return -1;
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity activity
     * @return 当前屏幕截图，包含状态栏
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        if (activity == null) return null;
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity activity
     * @return 当前屏幕截图，不包含状态栏
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        if (activity == null) return null;
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpVal   dpVal
     * @return px
     */
    public static float dp2px(Context context, float dpVal) {
        if (context == null) return -1;
        return dpVal * context.getResources().getDisplayMetrics().density;
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context context
     * @param spVal   spVal
     * @return sp
     */
    public static float sp2px(Context context, float spVal) {
        if (context == null) return -1;
        return spVal * context.getResources().getDisplayMetrics().scaledDensity;
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context context
     * @param pxVal   pxVal
     * @return px
     */
    public static float px2dp(Context context, float pxVal) {
        if (context == null) return -1;
        return pxVal / context.getResources().getDisplayMetrics().density;
    }

    /**
     * px转sp
     *
     * @param context context
     * @param pxVal   pxVal
     * @return px
     */
    public static float px2sp(Context context, float pxVal) {
        if (context == null) return -1;
        return pxVal / context.getResources().getDisplayMetrics().scaledDensity;
    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpVal   dpVal
     * @return px
     */
    public static int dp2pxInt(Context context, float dpVal) {
        if (context == null) return -1;
        return (int) (dpVal * context.getResources().getDisplayMetrics().density + 0.5f);
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context context
     * @param spVal   spVal
     * @return sp
     */
    public static int sp2pxInt(Context context, float spVal) {
        if (context == null) return -1;
        return (int) (spVal * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context context
     * @param pxVal   pxVal
     * @return px
     */
    public static int px2dpInt(Context context, float pxVal) {
        if (context == null) return -1;
        return (int) (pxVal / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * px转sp
     *
     * @param context context
     * @param pxVal   pxVal
     * @return px
     */
    public static int px2spInt(Context context, float pxVal) {
        if (context == null) return -1;
        return (int) (pxVal / context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }
}
