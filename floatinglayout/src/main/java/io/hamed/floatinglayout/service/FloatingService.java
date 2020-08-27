package io.hamed.floatinglayout.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.view.View;

import androidx.annotation.Nullable;

import io.hamed.floatinglayout.component.FloatingComponent;

/**
 * Author: Hamed Taherpour
 * *
 * Created: 7/30/2020
 * *
 * Address: https://github.com/HamedTaherpour
 */
public class FloatingService extends Service {

    public static final String EXTRA_LAYOUT_RESOURCE = "extra_layout_resource";
    public static final String EXTRA_RECEIVER = "extra_receiver";

    private FloatingComponent floatingComponent;
    // TODO: plz help me
    public static View baseView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            int layoutRes = intent.getIntExtra(EXTRA_LAYOUT_RESOURCE, -1);
            ResultReceiver receiver = intent.getParcelableExtra(EXTRA_RECEIVER);

            floatingComponent = new FloatingComponent(layoutRes, this, receiver);
            floatingComponent.setUp();
            baseView = floatingComponent.getFloatingWindowModule().getBaseView();
        }
        return START_STICKY_COMPATIBILITY;
    }

    @Override
    public void onDestroy() {
        floatingComponent.destroy();
        super.onDestroy();
    }

}
