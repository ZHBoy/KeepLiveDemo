package com.zhboy.keeplivedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/**
 * @author: zhou_hao
 * @date: 2020/10/20
 * @description:
 **/
class KeepLiveUtils {

    /**
     * 判断我们的应用是否在白名单中（防止系统休眠时，杀死我们自己的应用）
     *
     * @param context 上下文对象
     * @return true 在白名单中，false不在
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean isIgnoringBatteryOptimizations(Context context) {
        boolean isIgnoring = false;
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            isIgnoring = powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
        }
        return isIgnoring;
    }

    /**
     * 申请加入白名单
     *
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void requestIgnoreBatteryOptimizations(Context context) {
        try {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到指定应用的首页
     */
    public static void showActivity(Context context, @NonNull String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }

    /**
     * 跳转到指定应用的指定页面
     */
    public static void showActivity(Context context, @NonNull String packageName, @NonNull String activityDir) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityDir));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void goKeepLiveSetting(Context context) {
        if (isHuawei()) {
            goHuaweiSetting(context);

        } else if (isXiaomi()) {
            goXiaomiSetting(context);
        } else if (isOPPO()) {
            goOPPOSetting(context);
        } else if (isVIVO()) {
            goVIVOSetting(context);
        } else if (isMeizu()) {
            goMeizuSetting(context);
        } else if (isSamsung()) {
            goSamsungSetting(context);
        } else if (isSmartisan()) {
            goSmartisanSetting(context);
        } else if (isLeTV()) {
            goLetvSetting(context);
        }
    }

    /**
     * 华为
     **/
    public static boolean isHuawei() {
        if (Build.BRAND == null) {
            return false;
        } else {
            return Build.BRAND.toLowerCase().equals("huawei") || Build.BRAND.toLowerCase().equals("honor");
        }
    }

    public static void goHuaweiSetting(Context context) {
        try {
            showActivity(context, "com.huawei.systemmanager",
                    "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
        } catch (Exception e) {
            showActivity(context, "com.huawei.systemmanager",
                    "com.huawei.systemmanager.optimize.bootstart.BootStartActivity");
        }
    }

    /**
     * 小米
     **/
    public static boolean isXiaomi() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("xiaomi");
    }

    public static void goXiaomiSetting(Context context) {
        showActivity(context, "com.miui.securitycenter",
                "com.miui.permcenter.autostart.AutoStartManagementActivity");
    }

    /**
     * OPPO
     **/
    public static boolean isOPPO() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("oppo");
    }

    public static void goOPPOSetting(Context context) {
        try {
            showActivity(context, "com.coloros.phonemanager");
        } catch (Exception e1) {
            try {
                showActivity(context, "com.oppo.safe");
            } catch (Exception e2) {
                try {
                    showActivity(context, "com.coloros.oppoguardelf");
                } catch (Exception e3) {
                    showActivity(context, "com.coloros.safecenter");
                }
            }
        }
    }

    /**
     * VIVO
     **/
    public static boolean isVIVO() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("vivo");
    }

    public static void goVIVOSetting(Context context) {
        showActivity(context, "com.iqoo.secure");
    }

    /**
     * meizu
     **/
    public static boolean isMeizu() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("meizu");
    }

    public static void goMeizuSetting(Context context) {
        showActivity(context, "com.meizu.safe");
    }

    /**
     * 三星
     **/
    public static boolean isSamsung() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("samsung");
    }

    public static void goSamsungSetting(Context context) {
        try {
            showActivity(context, "com.samsung.android.sm_cn");
        } catch (Exception e) {
            showActivity(context, "com.samsung.android.sm");
        }
    }

    /**
     * 乐视
     **/
    public static boolean isLeTV() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("letv");
    }

    public static void goLetvSetting(Context context) {
        showActivity(context, "com.letv.android.letvsafe",
                "com.letv.android.letvsafe.AutobootManageActivity");
    }

    /**
     * 锤子
     **/
    public static boolean isSmartisan() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("smartisan");
    }

    public static void goSmartisanSetting(Context context) {
        showActivity(context, "com.smartisanos.security");
    }
}
