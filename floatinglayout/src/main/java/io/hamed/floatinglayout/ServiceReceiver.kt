package io.hamed.floatinglayout

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver


/**
 * Author: Hamed Taherpour
 * *
 * Created: 12/6/2019
 */
class ServiceReceiver(handler: Handler?, callBack: CallBack?) : ResultReceiver(handler) {

    private var callBack: CallBack? = callBack

    override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
        super.onReceiveResult(resultCode, resultData)
        if (resultCode == FloatingLayoutService.ACTION_ON_CLICK) {
            val resource = resultData.getInt(FloatingLayoutService.ID)
            callBack?.onClickListener(resource)
        }
        if (resultCode == FloatingLayoutService.ACTION_ON_CREATE) {
            callBack?.onCreateListener(FloatingLayoutService.mFloatingView)
        }
        if (resultCode == FloatingLayoutService.ACTION_ON_CLOSE) {
            callBack?.onCloseListener()
        }
    }
}