package io.hamed.floatinglayout

import android.content.Context
import android.content.Intent
import android.os.Handler
import androidx.annotation.LayoutRes


/**
 * Author: Hamed Taherpour
 * *
 * Created: 12/6/2019
 */
class FloatingLayout(
    mContext: Context,
    @LayoutRes mResource: Int,
    callBack: CallBack
) : FloatingLayoutContract {

    @LayoutRes
    private var mResource: Int = mResource
    private val mContext: Context = mContext
    private var callBack: CallBack = callBack

    private var isShow = false

    override fun onCreate() {
        val intent = Intent(mContext, FloatingLayoutService::class.java)
        intent.putExtra(FloatingLayoutService.LAYOUT_RESOURCE, mResource)
        if (callBack != null)
            intent.putExtra(FloatingLayoutService.RECEIVER, ServiceReceiver(Handler(), callBack))

        mContext.startService(intent)
    }

    override fun onClose() {
        mContext.stopService(Intent(mContext, FloatingLayoutService::class.java))
    }

    override fun create() {
        isShow = true
        onCreate()
    }

    override fun close() {
        isShow = false
        onClose()
    }

    override fun isShow(): Boolean {
        return isShow
    }

}