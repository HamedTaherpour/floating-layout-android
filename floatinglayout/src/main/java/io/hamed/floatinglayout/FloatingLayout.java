package io.hamed.floatinglayout;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.LayoutRes;

import io.hamed.floatinglayout.callback.FloatingListener;
import io.hamed.floatinglayout.service.FloatingService;

/**
 * Author: Hamed Taherpour
 * *
 * Created: 7/30/2020
 * *
 * Address: https://github.com/HamedTaherpour
 */
public class FloatingLayout {

    private Context context;
    private int layoutRes;
    private FloatingListener floatingListener;
    private boolean isShow;
    private Intent intent;

    public FloatingLayout(Context context, @LayoutRes int layoutRes) {
        this.context = context;
        this.layoutRes = layoutRes;
    }

    public void setFloatingListener(FloatingListener floatingListener) {
        this.floatingListener = floatingListener;
    }

    public void create() {
        isShow = true;
        Intent intent = getIntent();
        intent.putExtra(FloatingService.EXTRA_LAYOUT_RESOURCE, layoutRes);
        if (floatingListener != null)
            intent.putExtra(FloatingService.EXTRA_RECEIVER, new FloatingResult(new Handler(Looper.getMainLooper()), floatingListener));
        context.startService(intent);
    }

    public void destroy() {
        isShow = false;
        context.stopService(getIntent());
    }

    public boolean isShow() {
        return isShow;
    }

    private Intent getIntent() {
        if (intent == null)
            intent = new Intent(context, FloatingService.class);
        return intent;
    }
}

