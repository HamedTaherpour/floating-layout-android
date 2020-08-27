package io.hamed.floatinglayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import io.hamed.floatinglayout.callback.FloatingCallBack;
import io.hamed.floatinglayout.component.FloatingComponent;
import io.hamed.floatinglayout.service.FloatingService;

/**
 * Author: Hamed Taherpour
 * *
 * Created: 7/30/2020
 * *
 * Address: https://github.com/HamedTaherpour
 */
public class FloatingResult extends ResultReceiver {

    private FloatingCallBack floatingCallBack;

    public FloatingResult(Handler handler, FloatingCallBack floatingCallBack) {
        super(handler);
        this.floatingCallBack = floatingCallBack;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (resultCode == FloatingComponent.ACTION_ON_CREATE) {
            floatingCallBack.onCreateListener(FloatingService.baseView);
        }
        if (resultCode == FloatingComponent.ACTION_ON_CLOSE) {
            floatingCallBack.onCloseListener();
        }
        super.onReceiveResult(resultCode, resultData);
    }
}
