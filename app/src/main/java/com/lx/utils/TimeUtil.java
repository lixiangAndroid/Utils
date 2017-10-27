package com.lx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author: lixiang
 * @date: 2017/10/26 14:14
 * @description： 时间工具类
 */
public class TimeUtil {
    public static final  SimpleDateFormat DATE_FORMAT_ZERO    = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final  SimpleDateFormat DATE_FORMAT_ONE     = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public static final  SimpleDateFormat DATE_FORMAT_TWO     = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    public static final  SimpleDateFormat DATE_FORMAT_THREE   = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
    public static final  SimpleDateFormat DATE_FORMAT_FOUR    = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
    public static final  SimpleDateFormat DATE_FORMAT_FIVE    = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒", Locale.getDefault());
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = DATE_FORMAT_ZERO;
    private static       Calendar         INSTANCE            = Calendar.getInstance();

    private TimeUtil() {
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis 时间
     * @return 时间字符串
     */
    public static synchronized String getTimeInLong(long timeInMillis) {
        return getTimeInLong(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * long time to string
     *
     * @param timeInMillis 时间
     * @param dateFormat   转换格式
     * @return 时间字符串
     */
    public static synchronized String getTimeInLong(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param time 时间字符串
     * @return 时间长度
     */
    public static synchronized Long getTimeInString(String time) throws ParseException {
        return getDateInString(time).getTime();
    }

    /**
     * long time to string
     *
     * @param time       时间字符串
     * @param dateFormat 转换格式
     * @return 时间长度
     */
    public static synchronized Long getTimeInString(String time, SimpleDateFormat dateFormat) throws ParseException {
        return getDateInString(time, dateFormat).getTime();
    }

    /**
     * get current time in milliseconds
     *
     * @return 时间长度
     */
    public static synchronized long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return 时间字符串
     */
    public static synchronized String getCurrentTimeInString() {
        return getTimeInLong(getCurrentTimeInLong());
    }

    /**
     * get current time in string
     *
     * @return 时间字符串
     */
    public static synchronized String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTimeInLong(getCurrentTimeInLong(), dateFormat);
    }

    /**
     * get current date
     *
     * @return 当前时间
     */
    public static synchronized Date getCurrentDate() {
        return new Date(getCurrentTimeInLong());
    }

    /**
     * get date in milliseconds
     *
     * @return 时间
     */
    public static synchronized Date getDateInLong(long timeInMillis) {
        return new Date(timeInMillis);
    }

    /**
     * get date in string,format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return 时间
     */
    public static synchronized Date getDateInString(String time) throws ParseException {
        return getDateInString(time, DEFAULT_DATE_FORMAT);
    }

    /**
     * get date in string
     *
     * @return 时间
     */
    public static synchronized Date getDateInString(String time, SimpleDateFormat dateFormat) throws ParseException {
        return dateFormat.parse(time);
    }

    /**
     * get current week
     *
     * @return 星期
     */
    public static synchronized String getCurrentWeek() throws ParseException {
        return getCurrentWeek(DEFAULT_DATE_FORMAT);
    }

    /**
     * get current week ,format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return 星期
     */
    public static synchronized String getCurrentWeek(SimpleDateFormat dateFormat) throws ParseException {
        return getWeekInString(getCurrentTimeInString(), dateFormat);
    }

    /**
     * get week in string
     *
     * @return 星期
     */
    public static synchronized String getWeekInString(String time) throws ParseException {
        return getWeekInString(time, DEFAULT_DATE_FORMAT);
    }

    /**
     * get week in string,format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return 星期
     */
    public static synchronized String getWeekInString(String time, SimpleDateFormat dateFormat) throws ParseException {
        INSTANCE.setTime(getDateInString(time, dateFormat));
        return getWeekInInt(INSTANCE.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * get week in int
     *
     * @return 星期
     */
    public static String getWeekInInt(int week) {
        switch (week) {
            case 1:
                return "星期天";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    /**
     * get current month first day,format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return 当月的第一天
     */
    public static synchronized String getCurrentMonthFirstDay() {
        return getCurrentMonthFirstDay(DEFAULT_DATE_FORMAT);
    }

    /**
     * get current month first day
     *
     * @return 当月的第一天
     */
    public static synchronized String getCurrentMonthFirstDay(SimpleDateFormat dateFormat) {
        INSTANCE.setTime(getCurrentDate());
        INSTANCE.set(Calendar.DAY_OF_MONTH, 1);
        return dateFormat.format(INSTANCE.getTime());
    }

    /**
     * get current month first day,format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return 当月的第一天
     */
    public static synchronized String getNextMonthFirstDay() {
        return getNextMonthFirstDay(DEFAULT_DATE_FORMAT);
    }

    /**
     * get current month first day
     *
     * @return 当月的第一天
     */
    public static synchronized String getNextMonthFirstDay(SimpleDateFormat dateFormat) {
        INSTANCE.setTime(getCurrentDate());
        INSTANCE.set(Calendar.DAY_OF_MONTH, 1);
        INSTANCE.add(Calendar.MONTH, 1);
        return dateFormat.format(INSTANCE.getTime());
    }

    /**
     * get seven day,format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return 七天前
     */
    public static synchronized String getSevenDayAgo() {
        return getSevenDayAgo(DEFAULT_DATE_FORMAT);
    }

    /**
     * get seven day
     *
     * @return 七天前
     */
    public static synchronized String getSevenDayAgo(SimpleDateFormat dateFormat) {
        INSTANCE.setTime(getCurrentDate());
        INSTANCE.add(Calendar.DAY_OF_MONTH, -7);
        return dateFormat.format(INSTANCE.getTime());
    }
}
