package io.hamed.floatinglayout.component;

import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;

import io.hamed.floatinglayout.module.FloatingViewMovementModule;
import io.hamed.floatinglayout.module.FloatingWindowModule;

/**
 * Author: Hamed Taherpour
 * *
 * Created: 7/30/2020
 * *
 * Address: https://github.com/HamedTaherpour
 */
public class FloatingComponent {

    public static final int ACTION_ON_CREATE = 0x12;
    public static final int ACTION_ON_CLOSE = 0x11;

    private int layoutRes;
    private Context context;
    private ResultReceiver receiver;
    private FloatingWindowModule floatingWindowModule;
    private FloatingViewMovementModule floatingViewMovementModule;

    public FloatingComponent(int layoutRes, Context context, ResultReceiver receiver) {
        this.layoutRes = layoutRes;
        this.context = context;
        this.receiver = receiver;
    }

    public void setUp() {
        int ROOT_CONTAINER_ID = context.getResources().getIdentifier("root_container", "id", context.getPackageName());
        floatingWindowModule = new FloatingWindowModule(context, layoutRes);
        floatingWindowModule.create();
        View floatingView = floatingWindowModule.getBaseView();
        View rootContainer = floatingView.findViewById(ROOT_CONTAINER_ID);
        floatingViewMovementModule = new FloatingViewMovementModule(floatingWindowModule.getParams(), rootContainer, floatingWindowModule.getWindowManager(), floatingView);
        floatingViewMovementModule.run();
//        setOnClickToView(rootContainer);

        sendAction(ACTION_ON_CREATE, new Bundle());
    }

    public FloatingWindowModule getFloatingWindowModule() {
        return floatingWindowModule;
    }

    private void sendAction(int action, Bundle bundle) {
        receiver.send(action, bundle);
    }

    public void destroy() {
        sendAction(ACTION_ON_CLOSE, new Bundle());

        if (floatingWindowModule != null) {
            floatingWindowModule.destroy();
            floatingWindowModule = null;
        }
        if (floatingViewMovementModule != null) {
            floatingViewMovementModule.destroy();
            floatingViewMovementModule = null;
        }
    }
}
