package com.muhammadfarhaan.latihanshared.Utils

import android.content.Context
import android.content.SharedPreferences

import com.muhammadfarhaan.latihanshared.Model.UserModel

class Preferences(context:Context) {
    private val context:Context
    init{
        this.context = context
    }
    companion object {
        private val PREF_SESSION = "com.muhammadfarhaan.latihanshared.session"
        private val REGISTER_USERNAME = "REGISTER_USERNAME"
        private val REGISTER_PASSWORD = "REGISTER_PASSWORD"
        private val REGISTER_PHONE = "REGISTER_PHONE"
        private val LOGIN_STATUS = "LOGIN_STATUS"
        fun setUserPreferences(context:Context, userModel:UserModel) {
            val preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString(REGISTER_USERNAME, userModel.getUsername())
            editor.putString(REGISTER_PASSWORD, userModel.getPassword())
            editor.putString(REGISTER_PHONE, userModel.getPhone())
            editor.apply()
        }
        fun getRegisteredUser(context:Context):String? {
            val preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE)
            return preferences.getString(REGISTER_USERNAME, UtilStatic.DEFAULT_STRING)
        }
        fun getRegisteredPassword(context:Context):String? {
            val preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE)
            return preferences.getString(REGISTER_PASSWORD, UtilStatic.DEFAULT_STRING)
        }
        fun getRegisteredPhone(context:Context):String? {
            val preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE)
            return preferences.getString(REGISTER_PHONE, UtilStatic.DEFAULT_STRING)
        }
        /** Deklarasi Edit Preferences dan mengubah data
         * yang memiliki key KEY_STATUS_SEDANG_LOGIN dengan parameter status */
        fun setLoggedInStatus(context:Context, statusLogin:Boolean) {
            val preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putBoolean(LOGIN_STATUS, statusLogin)
            editor.apply()
        }
        fun getLoggedInStatus(context:Context):Boolean {
            val preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE)
            return preferences.getBoolean(LOGIN_STATUS, UtilStatic.DEFAULT_BOOLEAN)
        }
        fun setLogout(context:Context) {
            val preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.remove(LOGIN_STATUS)
            editor.apply()
        }
    }
}