package me.isming.tools.cvfilter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;

/**
 * Created by sam on 14-9-13.
 */
public class UIUtils {
    /** 屏幕宽度   */
    private static int sDisplayWidthPixels = 0;
    /** 屏幕高度   */
    private static int sDisplayheightPixels = 0;

    private static float sDensity = 0.0f;
    private static float sScaledDensity = 0.0f;

    private static volatile Handler sHandler;


    /**
     * 获取MainLooper的handler
     * @return
     */
    public static Handler getHandler() {

        if (sHandler == null) {
            synchronized (UIUtils.class) {
                if (sHandler == null) {
                    sHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return sHandler;
    }

    /**
     * 在UI线程执行
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        if (runnable != null) {
            getHandler().post(runnable);
        }
    }


    /**
     * 获取屏幕参数
     */
    private static synchronized void getDisplayMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        sDisplayWidthPixels = dm.widthPixels;// 宽度
        sDisplayheightPixels = dm.heightPixels;// 高度
        sScaledDensity = dm.scaledDensity;
        sDensity = dm.density;
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getDisplayWidthPixels(Context context) {
        if (sDisplayWidthPixels == 0) {
            getDisplayMetrics(context);
        }
        return sDisplayWidthPixels;
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public static int getDisplayheightPixels(Context context) {
        if (sDisplayheightPixels == 0) {
            getDisplayMetrics(context);
        }
        return sDisplayheightPixels;
    }

    /**
     * 将px值转换为dip或dp值
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        if (Float.compare(sDensity, 0.0f) == 0) {
            getDisplayMetrics(context);
        }
        return (int) (pxValue / sDensity + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值
     *
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        if (Float.compare(sDensity, 0.0f) == 0) {
            getDisplayMetrics(context);
        }
        return (int) (dipValue * sDensity + 0.5f);
    }

    /**
     * 将px值转换为sp值
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        if (Float.compare(sScaledDensity, 0.0f) == 0) {
            getDisplayMetrics(context);
        }
        return (int) (pxValue / sScaledDensity + 0.5f);
    }

    /**
     * 将sp值转换为px值
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        if (Float.compare(sScaledDensity, 0.0f) == 0) {
            getDisplayMetrics(context);
        }
        return (int) (spValue * sScaledDensity + 0.5f);
    }
}
