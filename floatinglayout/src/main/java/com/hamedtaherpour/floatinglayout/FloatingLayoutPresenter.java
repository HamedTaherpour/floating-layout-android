package com.hamedtaherpour.floatinglayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.LayoutRes;
import android.view.View;


public class FloatingLayoutPresenter {

    private Context mContext;
    public FloatingLayout.CallBack callBack;
    public @LayoutRes
    int resource;
    private IView mView;
    private FLGravity flGravity;

    public FloatingLayoutPresenter(Context mContext, FloatingLayout.CallBack callBack, int resource, IView mView,FLGravity flGravity) {
        this.mContext = checkNull("Context can not be null", mContext);
        this.callBack = callBack;
        this.resource = resource;
        this.mView = mView;
        this.flGravity = flGravity;
    }

    public void onCreate() {
        Intent intent = new Intent(mContext, FloatingLayoutService.class);
        intent.putExtra(FloatingLayoutService.LAYOUT_RESOURCE, resource);
        if (callBack != null)
            intent.putExtra(FloatingLayoutService.RECEIVER, new DownloadReceiver(new Handler()));
        if (flGravity != null)
            intent.putExtra(FloatingLayoutService.FL_GRAVITY, flGravity);

        mContext.startService(intent);
    }

    public void onClose() {
        mContext.stopService(new Intent(mContext, FloatingLayoutService.class));
    }

    private class DownloadReceiver extends ResultReceiver {
        public DownloadReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == FloatingLayoutService.ACTION_ON_CLICK) {
                int resource = resultData.getInt(FloatingLayoutService.ID);
                mView.onClickListener(resource);
            }
            if (resultCode == FloatingLayoutService.ACTION_ON_CREATE) {
                mView.onCreateListener(FloatingLayoutService.mFloatingView);
            }
            if (resultCode == FloatingLayoutService.ACTION_ON_CLOSE) {
                mView.onCloseListener();
            }
        }
    }

    private <T> T checkNull(String message, T object) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }

    public interface IView {
        void onClickListener(int resourceId);

        void onCreateListener(View view);

        void onCloseListener();
    }
}
