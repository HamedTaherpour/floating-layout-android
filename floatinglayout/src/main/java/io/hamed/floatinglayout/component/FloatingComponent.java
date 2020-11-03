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

    public static final int ACTION_ON_CREATE =  0x0002;
    public static final int ACTION_ON_CLOSE =   0x00001;

    private int layoutRes;
    private Context context;
    private ResultReceiver receiver;
    private FloatingWindowModule floatingWindowModule;
    private FloatingViewMovementModule floatingViewMovementModule;

    public FloatingComponent(int layoutRes, Context context) {
        this.layoutRes = layoutRes;
        this.context = context;
    }

    public void setUp() {
        int ROOT_CONTAINER_ID = getViewRootId();

        floatingWindowModule = new FloatingWindowModule(context, layoutRes);
        floatingWindowModule.create();

        View floatingView = floatingWindowModule.getView();
        View rootContainer = floatingView.findViewById(ROOT_CONTAINER_ID);

        floatingViewMovementModule = new FloatingViewMovementModule(floatingWindowModule.getParams(), rootContainer, floatingWindowModule.getWindowManager(), floatingView);
        floatingViewMovementModule.run();

        sendAction(ACTION_ON_CREATE, new Bundle());
    }

    public void setReceiver(ResultReceiver receiver) {
        this.receiver = receiver;
    }

    public int getViewRootId(){
        return context.getResources().getIdentifier("root_container", "id", context.getPackageName());
    }

    public FloatingWindowModule getFloatingWindowModule() {
        return floatingWindowModule;
    }

    private void sendAction(int action, Bundle bundle) {
        if (receiver != null)
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
