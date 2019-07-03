package com.hamedtaherpour.floatinglayout;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class FloatingLayoutService extends Service implements View.OnClickListener {

    public static final String LAYOUT_RESOURCE = "layout-resource";
    public static final String RECEIVER = "receiver";
    public static final int ACTION_ON_CLICK = 3265;
    public static final int ACTION_ON_CREATE = 5874;
    public static final int ACTION_ON_CLOSE = 3625;
    public static final String ID = "id";

    private Context mContext = null;
    private Handler mHandler = null;
    private Runnable mRunnable = null;
    private ResultReceiver receiver = null;

    private View rootContainer;
    // IView
    private WindowManager mWindowManager;
    public static View mFloatingView;
    private @LayoutRes
    int resource;
    private @IdRes
    int ROOT_CONTAINER_ID;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHandler = new Handler();

        ROOT_CONTAINER_ID = getResources().getIdentifier("root_container", "id", getPackageName());
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        resource = intent.getIntExtra(LAYOUT_RESOURCE, 0);
        receiver = (ResultReceiver) intent.getParcelableExtra(RECEIVER);

        onDestroyView();
        createView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mContext != null) mContext = null;
        if (mHandler != null) mHandler = null;
        if (mRunnable != null) mRunnable = null;
        onClose();
        onDestroyView();
    }

    private void createView() {

        final WindowManager.LayoutParams params;

        int windowType = WindowManager.LayoutParams.TYPE_PHONE;
        // Set to TYPE_SYSTEM_ALERT so that the Service can display it
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            windowType = WindowManager.LayoutParams.TYPE_TOAST;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            windowType = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            windowType = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }

        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                windowType,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mFloatingView = layoutInflater.inflate(resource, null);

        params.gravity = Gravity.CENTER | Gravity.CENTER;
        params.x = 0;
        params.y = 0;
        mWindowManager.addView(mFloatingView, params);

        rootContainer = mFloatingView.findViewById(ROOT_CONTAINER_ID);
        if (rootContainer != null) {
            // get position for moving
            rootContainer.setOnTouchListener(new View.OnTouchListener() {
                private int initialX;
                private int initialY;
                private float initialTouchX;
                private float initialTouchY;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            //remember the initial position.
                            initialX = params.x;
                            initialY = params.y;

                            //get the touch location
                            initialTouchX = event.getRawX();
                            initialTouchY = event.getRawY();
                            return true;
                        case MotionEvent.ACTION_MOVE:
                            //Calculate the X and Y coordinates of the view.
                            params.x = initialX + (int) (event.getRawX() - initialTouchX);
                            params.y = initialY + (int) (event.getRawY() - initialTouchY);
                            //Update the layout with new X & Y coordinate
                            mWindowManager.updateViewLayout(mFloatingView, params);
                            return true;
                    }
                    return false;
                }
            });
            // set onclick child
            setOnClickToView(rootContainer);
        }

        receiver.send(ACTION_ON_CREATE, null);
    }

    private void setOnClickToView(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int idx = 0; idx < group.getChildCount(); idx++) {
                setOnClickToView(group.getChildAt(idx));
            }
        } else if (view.getTag() instanceof String) {
            String tag = view.getTag().toString().toLowerCase();
            if (("click").equals(tag))
                view.setOnClickListener(this);
        }
    }

    private void onDestroyView() {
        if (mWindowManager != null) mWindowManager.removeView(mFloatingView);
    }

    private void onClose() {
        if (receiver != null)
            receiver.send(ACTION_ON_CLOSE, null);
    }

    @Override
    public void onClick(View v) {
        Bundle resultData = new Bundle();
        resultData.putInt(ID, v.getId());
        receiver.send(ACTION_ON_CLICK, resultData);
    }

}
