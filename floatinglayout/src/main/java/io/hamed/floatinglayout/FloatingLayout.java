package io.hamed.floatinglayout;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import androidx.annotation.LayoutRes;

import io.hamed.floatinglayout.callback.FloatingCallBack;
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
    private FloatingCallBack floatingCallBack;
    private boolean isShow;

    public FloatingLayout(Context context, @LayoutRes int layoutRes, FloatingCallBack floatingCallBack) {
        this.context = context;
        this.layoutRes = layoutRes;
        this.floatingCallBack = floatingCallBack;
    }

    public void create() {
        isShow = true;
        Intent intent = new Intent(context, FloatingService.class);
        intent.putExtra(FloatingService.EXTRA_LAYOUT_RESOURCE, layoutRes);
        intent.putExtra(FloatingService.EXTRA_RECEIVER, new FloatingResult(new Handler(), floatingCallBack));
        context.startService(intent);
    }

    public void close() {
        isShow = false;
        context.stopService(new Intent(context, FloatingService.class));
    }

    public boolean isShow() {
        return isShow;
    }
}

