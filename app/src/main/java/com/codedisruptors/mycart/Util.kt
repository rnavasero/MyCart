package com.example.mystore

import android.text.TextUtils
import android.widget.EditText

/**
 * Created by student on 27/02/2018.
 */
object Util {

    fun isEmpty(editText: EditText, msg: String) :Boolean{
        if(TextUtils.isEmpty(editText.text)) {
            editText.error= msg
            return false
        }

        return true

    }
}