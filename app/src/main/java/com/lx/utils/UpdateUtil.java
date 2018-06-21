package com.lx.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixiang
 * @date: 2018/06/14 13:55
 * @description： 版本检测更新
 */
public class UpdateUtil {

    /**
     * 解析服务器端升级配置文件updateConfig.xml的内容
     */
    public static Map<String, String> parseConfigXML(InputStream inStream) {
        XmlPullParser parser = Xml.newPullParser();
        Map<String, String> info;
        try {
            parser.setInput(inStream, "utf-8");// 设置解析的数据源
            int type = parser.getEventType();
            info = new HashMap<>();
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        if ("version".equals(parser.getName())) {

                            info.put("version", parser.nextText());

                        } else if ("url".equals(parser.getName())) {

                            info.put("url", parser.nextText());

                        } else if ("name".equals(parser.getName())) {

                            info.put("name", parser.nextText());
                        }
                        break;
                }
                type = parser.next();
            }
        } catch (Exception exception) {
            info = null;
        }
        return info;
    }

    /**
     * 检测升级
     */
    public static void checkUpdate(final Context context) {
//        OkGo.<String>get(URLUtil.getApkUpdateConfig())
//                .execute(new StringCallback() {
//
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        byte[] bytes = response.body().getBytes();
//                        InputStream inStream = new ByteArrayInputStream(bytes);
////                            	String string = convertStreamToString(inStream);
//                        Map<String, String> updateConfig = parseConfigXML(inStream);
//                        if (updateConfig != null) {
//                            String temp = updateConfig.get("version");
//                            int versionCode = Integer.parseInt(TextUtils.isEmpty(temp) ? "1" : temp);
//                            LogUtil.d("UpdateUtil", "当前版本：" + getVersionCode() + "--->网络版本：" + versionCode);
//                            String name = updateConfig.get("name");
//                            if (versionCode > getVersionCode()) {/* 需要升级 */
//                                LogUtil.e("step1");
//                                download(context, URLUtil.getApkUpdate(), name, name);
//                            }
//                        }
//                    }
//                });
//        HttpUtil.getClient().get(updateConfigUrl,
//                new AsyncHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
//                        try {
//                            String s = new String(arg2);
//                            DebugUtil.i("string ", s + "123");
//                            byte[] bytes = s.getBytes();
//                            InputStream inStream = new ByteArrayInputStream(bytes);
////                            	String string = convertStreamToString(inStream);
//                            Map<String, String> updateConfig = parseConfigXML(inStream);
//                            if (updateConfig != null) {
//                                String temp = updateConfig.get("version");
//                                int versionCode = Integer.parseInt(TextUtils.isEmpty(temp) ? "1" : temp);
//                                DebugUtil.d("UpdateUtil", "当前版本：" + getVersionCode() + "--->网络版本：" + versionCode);
//                                String name = updateConfig.get("name");
//                                if (versionCode > getVersionCode()) {/* 需要升级 */
//                                    if (isShowAskDialog) {/* 需要展示询问是否升级对话框 */
//                                        showAskDialog(name);
//                                    } else {
//                                        downLoadAPK(name);
//                                    }
//                                }
//                            }
//                            super.onSuccess(arg0, arg1, arg2);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                });
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 显示提示软件有更新的界面 询问用户是否进行更新
     */
//    private static void showAskDialog(String name) {
//        AlertDialog.Builder builder = new Builder(context);
//        builder.setTitle("软件更新");
//        builder.setMessage("发现新版本,是否更新？");
//
//		/* 确定操作 */
//        builder.setPositiveButton("现在更新", new OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                downLoadAPK(name);
//            }
//        });
//
//		/* 取消操作 */
//        builder.setNegativeButton("暂不更新", new OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();// 关闭对话框
//            }
//        });
//        Dialog noticeDialog = builder.create();
//        noticeDialog.show();
//    }

    /**
     * 下载APK文件
     *
     * @param name APK文件的下载地址 描述：用系统自带的DownloadManager实现下载功能
     */
    private static long downLoadAPK(String uri, String title, String name) {
        LogUtil.e("step3");
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));
//        request.setAllowedNetworkTypes(Request.NETWORK_MOBILE | Request.NETWORK_WIFI);
        File folder = new File("/download/lanyang");
        if (!folder.exists() || !folder.isDirectory()) {
            folder.mkdirs();
        }
        request.setDestinationInExternalPublicDir(folder.toString(), name + ".apk");
        request.setTitle(title);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        AppContext.getAppContext().registerReceiver(new DownLoadCompleteReceiver(), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        return getDownloadManager().enqueue(request);
    }

    /**
     * 安装APK文件
     *
     * @param context Context
     * @param uri     描述:根据APK本地的路径 调用系统的安装视图
     */
    public static void startInstall(Context context, Uri uri) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(install);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void download(Context context, String url, String title, String appName) {
        // 获取存储ID
        long downloadId = SPUtil.getLong(context, DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
        if (downloadId != -1L) {
            int status = getDownloadStatus(downloadId);
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                //启动更新界面
                Uri uri = getDownloadUri(downloadId);
                if (uri != null) {
                    if (compare(getApkInfo(uri.getPath()))) {
//                        installSlient(uri.getPath());
                        startInstall(context, uri);
                        return;
                    } else {
                        getDownloadManager().remove(downloadId);
                    }
                }
                startDownLoadAPK(url, title, appName);
            } else if (status == DownloadManager.STATUS_FAILED) {
                startDownLoadAPK(url, title, appName);
            } else {
                startDownLoadAPK(url, title, appName);
                LogUtil.d("apk is already downloading");
            }
        } else {
            startDownLoadAPK(url, title, appName);
        }
    }

    private static void startDownLoadAPK(String url, String title, String appName) {
        LogUtil.e("step2");
        long id = downLoadAPK(url, title, appName);
        SPUtil.put(AppContext.getAppContext(), DownloadManager.EXTRA_DOWNLOAD_ID, id);
    }


    /**
     * 获取apk程序信息[packageName,versionName...]
     *
     * @param path apk path
     */
    private static PackageInfo getApkInfo(String path) {
        PackageManager pm = AppContext.getAppContext().getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            return info;
        }
        return null;
    }


    /**
     * 下载的apk和当前程序版本比较
     *
     * @param apkInfo apk file's packageInfo
     * @return 如果当前应用版本小于apk的版本则返回true
     */
    private static boolean compare(PackageInfo apkInfo) {
        if (apkInfo == null) {
            return false;
        }
        String localPackage = AppContext.getAppContext().getPackageName();
        if (apkInfo.packageName.equals(localPackage)) {
            try {
                PackageInfo packageInfo = AppContext.getAppContext().getPackageManager().getPackageInfo(localPackage, 0);
                if (apkInfo.versionCode > packageInfo.versionCode) {
                    return true;
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 获取文件保存的路径
     *
     * @param downloadId an ID for the download, unique across the system.
     *                   This ID is used to make future calls related to this download.
     * @return file path
     * @see UpdateUtil#getDownloadUri(long)
     */
    public static String getDownloadPath(long downloadId) {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor c = getDownloadManager().query(query);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    return c.getString(c.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI));
                }
            } finally {
                c.close();
            }
        }
        return null;
    }

    /**
     * 获取保存文件的地址
     *
     * @param downloadId an ID for the download, unique across the system.
     *                   This ID is used to make future calls related to this download.
     * @see UpdateUtil#getDownloadPath(long)
     */
    public static Uri getDownloadUri(long downloadId) {
        return getDownloadManager().getUriForDownloadedFile(downloadId);
    }

    private static DownloadManager getDownloadManager() {
        return (DownloadManager) AppContext.getAppContext().getSystemService(Context.DOWNLOAD_SERVICE);
    }

    /**
     * 获取下载状态
     *
     * @param downloadId an ID for the download, unique across the system.
     *                   This ID is used to make future calls related to this download.
     * @return int
     * @see DownloadManager#STATUS_PENDING
     * @see DownloadManager#STATUS_PAUSED
     * @see DownloadManager#STATUS_RUNNING
     * @see DownloadManager#STATUS_SUCCESSFUL
     * @see DownloadManager#STATUS_FAILED
     */
    private static int getDownloadStatus(long downloadId) {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor c = getDownloadManager().query(query);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    return c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
                }
            } finally {
                c.close();
            }
        }
        return -1;
    }

    //静默安装
    public static void installSlient(String path) {
        LogUtil.e("step5");
        LogUtil.e(path);
        String cmd = "pm install -r " + path;
        Process process = null;
        DataOutputStream os = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        try {
            //静默安装需要root权限
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.write(cmd.getBytes());
            os.writeBytes("\n");
            os.writeBytes("exit\n");
            os.flush();
            //执行命令
            process.waitFor();
            //获取返回结果
            successMsg = new StringBuilder();
            errorMsg = new StringBuilder();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }
            while ((s = errorResult.readLine()) != null) {
                errorMsg.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (process != null) {
                    process.destroy();
                }
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //显示结果
        LogUtil.e("成功消息：" + successMsg.toString() + "\n" + "错误消息: " + errorMsg.toString()
        );
    }

    /**
     * 删除已下载的文件
     */
    public static void removeFile(Context context) {
        long downloadId = SPUtil.getLong(context, DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
        if (downloadId != -1L) {
            String downloadPath = getDownloadPath(downloadId);
            if (downloadPath != null) {
                File file = new File(downloadPath);
                if (file.exists()) {
                    if (!compare(getApkInfo(downloadPath))) {
                        if (file.delete()) {
                            SPUtil.put(context, DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
                        }
                    }
                }
            }
        }
    }

    /**
     * isAccessibilitySettingsOn
     */
//    public static boolean isAccessibilityOn(Context mContext) {
//        int accessibilityEnabled = 0;
//        final String service = mContext.getPackageName() + "/" + AutomaticInstallationService.class.getCanonicalName();
//        try {
//            accessibilityEnabled = Settings.Secure.getInt(
//                    mContext.getApplicationContext().getContentResolver(),
//                    Settings.Secure.ACCESSIBILITY_ENABLED);
//            LogUtil.v("accessibilityEnabled = " + accessibilityEnabled);
//        } catch (Settings.SettingNotFoundException e) {
//            LogUtil.e("Error finding setting, default accessibility to not found: " + e.getMessage());
//        }
//        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
//
//        if (accessibilityEnabled == 1) {
//            LogUtil.v("***ACCESSIBILITY IS ENABLED*** -");
//            String settingValue = Settings.Secure.getString(mContext.getApplicationContext().getContentResolver(), Settings.Secure
//                    .ENABLED_ACCESSIBILITY_SERVICES);
//            if (settingValue != null) {
//                mStringColonSplitter.setString(settingValue);
//                while (mStringColonSplitter.hasNext()) {
//                    String accessibilityService = mStringColonSplitter.next();
//
//                    LogUtil.v(" accessibilityService :: " + accessibilityService + " " + service);
//                    if (accessibilityService.equalsIgnoreCase(service)) {
//                        LogUtil.v("We've found the correct setting - accessibility is switched on!");
//                        return true;
//                    }
//                }
//            }
//        } else {
//            LogUtil.v("---ACCESSIBILITY IS DISABLED--");
//        }
//
//        return false;
//    }
}
