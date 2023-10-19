package com.lgcns.ffmpegkit

class ReturnCode(@JvmField val value: Int) {

    val isValueSuccess: Boolean
        get() = value == SUCCESS
    val isValueError: Boolean
        get() = value != SUCCESS && value != CANCEL
    val isValueCancel: Boolean
        get() = value == CANCEL

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        @JvmField
        var SUCCESS = 0
        @JvmField
        var CANCEL = 255
        fun isSuccess(returnCode: ReturnCode?): Boolean {
            return returnCode != null && returnCode.value == SUCCESS
        }

        fun isCancel(returnCode: ReturnCode?): Boolean {
            return returnCode != null && returnCode.value == CANCEL
        }
    }
}