package io.hamed.floatinglayout.module;

import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Author: Hamed Taherpour
 * *
 * Created: 7/30/2020
 * *
 * Address: https://github.com/HamedTaherpour
 */
public class FloatingViewMovementModule {

    private WindowManager.LayoutParams params;
    private View rootContainer;
    private WindowManager windowManager;
    private View baseView;

    public FloatingViewMovementModule(WindowManager.LayoutParams params, View rootContainer, WindowManager windowManager, View baseView) {
        this.params = params;
        this.rootContainer = rootContainer;
        this.windowManager = windowManager;
        this.baseView = baseView;
    }

    public void run() {
        if (rootContainer != null) { // get position for moving
            rootContainer.setOnTouchListener(new View.OnTouchListener() {

                private int initialX = 0;
                private int initialY = 0;
                private float initialTouchX = 0f;
                private float initialTouchY = 0f;

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
                            params.x = (int) (initialX + (event.getRawX() - initialTouchX));
                            params.y = (int) (initialY + (event.getRawY() - initialTouchY));
                            //Update the layout with new X & Y coordinate
                            windowManager.updateViewLayout(baseView, params);
                            return true;
                    }
                    return false;
                }
            });
        }
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
