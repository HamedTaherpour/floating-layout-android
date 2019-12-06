package io.hamed.floatinglayout

import android.view.View


/**
 * Author: Hamed Taherpour
 * *
 * Created: 12/6/2019
 */
interface CallBack {

    fun onClickListener(resourceId: Int)

    fun onCreateListener(view: View?)

    fun onCloseListener()
}