package io.hamed.floatinglayout.module;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
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
    private View view;
    private WindowManager windowManager;

    public FloatingWindowModule(Context context, @LayoutRes int layoutRes) {
        this.context = context;
        this.layoutRes = layoutRes;
    }

    public void create() {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(getView(), getParams());
    }

    public WindowManager.LayoutParams getParams() {
        if (params == null)
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    getWindowType(),
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
            );
        return params;
    }

    public View getView() {
        if (view == null)
            view = View.inflate(context, layoutRes, null);
        return view;
    }

    public int getWindowType() {
        // Set to TYPE_SYSTEM_ALERT so that the Service can display it
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
    }

    public WindowManager getWindowManager() {
        return windowManager;
    }

    public void destroy() {
        try {
            if (windowManager != null)
                if (view != null)
                    windowManager.removeViewImmediate(view);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            params = null;
            view = null;
            windowManager = null;
        }
    }
}
