package io.hamed.floatinglayout


/**
 * Author: Hamed Taherpour
 * *
 * Created: 12/6/2019
 */
interface FloatingLayoutContract {

    fun onCreate()

    fun onClose()

    fun create()

    fun close()

    fun isShow(): Boolean
}