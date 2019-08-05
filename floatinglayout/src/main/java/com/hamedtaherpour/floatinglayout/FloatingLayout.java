package com.hamedtaherpour.floatinglayout;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;


public class FloatingLayout implements FloatingLayoutPresenter.IView {

    public interface CallBack {
        void onClickListener(int resourceId);

        void onCreateListener(View view);

        void onCloseListener();
    }

    private CallBack callBack;
    private FloatingLayoutPresenter presenter;
    private boolean isShow = false;

    public FloatingLayout(Context mContext, @LayoutRes int resource, CallBack callBack) {
        this.callBack = callBack;
        presenter = new FloatingLayoutPresenter(mContext, callBack, resource, this, null);
    }

    public FloatingLayout(Context mContext, @LayoutRes int resource, FLGravity flGravity, CallBack callBack) {
        this.callBack = callBack;
        presenter = new FloatingLayoutPresenter(mContext, callBack, resource, this, flGravity);
    }

    public void create() {
        isShow = true;
        if (presenter != null)
            presenter.onCreate();
    }

    public boolean isShow() {
        return isShow;
    }

    public void close() {
        isShow = false;
        if (presenter != null)
            presenter.onClose();
    }

    public View getView() {
        return FloatingLayoutService.mFloatingView;
    }

    @Override
    public void onClickListener(int resource) {
        callBack.onClickListener(resource);
    }

    @Override
    public void onCreateListener(View view) {
        callBack.onCreateListener(view);
    }

    @Override
    public void onCloseListener() {
        callBack.onCloseListener();
    }

}
