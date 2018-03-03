package com.codedisruptors.mycart

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Session (context:Context) {

    private var mPreference: SharedPreferences
    private var editor:SharedPreferences.Editor

    init {
        mPreference = context.getSharedPreferences(USER_PREFERENCE,MODE_PRIVATE)
        editor      = mPreference.edit()
    }

    fun getUserProfile():String{
        return mPreference.getString(USER_PROFILE,"")
    }

    fun authorize(raw:String){
        editor.putString(USER_PROFILE,raw).apply()
        setUserActive(true)
    }

    fun setUserActive(isUserActive:Boolean){
        editor.putBoolean(USER_STATUS,isUserActive).apply()
    }


    fun isUserActive():Boolean{

        return mPreference.getBoolean(USER_PREFERENCE,false)
    }


    companion object {
        val USER_PREFERENCE = "com.codedisruptors.mycart"
        val USER_STATUS     = "userStatus"
        var USER_PROFILE    = "userProfile"
    }
}