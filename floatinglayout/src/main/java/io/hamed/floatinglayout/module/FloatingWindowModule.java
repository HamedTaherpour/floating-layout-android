package io.hamed.floatinglayout.module;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;

/**
 * Author: Hamed Taherpour
 * *
 * Created: 7/30/2020
 * *
 * Address: https://github.com/HamedTaherpour
 */
public class FloatingWindowModule {

    private int layoutRes;
    private Context context;

    private WindowManager.LayoutParams params;
    private View baseView;
    private WindowManager windowManager;

    public FloatingWindowModule(Context context, @LayoutRes int layoutRes) {
        this.context = context;
        this.layoutRes = layoutRes;
    }

    public void create() {
        // Set to TYPE_SYSTEM_ALERT so that the Service can display it
        int windowType = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;

        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                windowType,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        baseView = View.inflate(context, layoutRes, null);
        windowManager.addView(baseView, params);
    }

    public WindowManager.LayoutParams getParams() {
        return params;
    }

    public View getBaseView() {
        return baseView;
    }

    public WindowManager getWindowManager() {
        return windowManager;
    }

    public void destroy() {
        try {
            if (windowManager != null)
                if (baseView != null)
                    windowManager.removeViewImmediate(baseView);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            params = null;
            baseView = null;
            windowManager = null;
        }
    }
}
