package com.example.mycameraapp.session

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.mycameraapp.util.GsonUtils
import java.util.*

class SessionManager : Repository<Session> {

    private var TAG = "Session"

    companion object {
        val TAG = SessionManager::class.java.simpleName
        private var sharedPreferences: SharedPreferences? = null
        // Editor for Shared preferences
        var editor: SharedPreferences.Editor? = null

        // Shared sharedPreferences mode
        private var PRIVATE_MODE = 0

        var PREF_NAME = "USER_PREFERENCE"


        var sessionManager: SessionManager? = null


        fun getInstance(context: Context): SessionManager? {
            if (sessionManager == null) {
                sessionManager = SessionManager(context)
            }
            return sessionManager!!
        }
    }

    // Context
    var context: Context

    // default constructor
    private constructor(context: Context) {
        this.context = context
        initSharedPreferences()
    }

    fun initSharedPreferences() {
        sharedPreferences =this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = sharedPreferences!!.edit()
    }


    override fun persist(entity: Session) {
        if (entity == null) {
            Log.d(TAG," cant persist")
            return

        }
        editor!!.putString(Session.TAG, GsonUtils.toString(entity))
        editor!!.commit()

    }

    override fun update(entity: Session) {

        if (entity == null) {
            Log.d(TAG," cant update")
            return
        }

        editor!!.putString(Session.Companion.TAG, GsonUtils.toString(entity))
        editor!!.commit()
    }


    override fun findById(id: Objects): Session? {

        return null
    }

    override fun findOne(): Session? {
        var sessionData = sharedPreferences?.getString(Session.Companion.TAG, null)
        if (sessionData != null) {
            var session = GsonUtils.toObject(sessionData, Session::class.java)
            return session
        } else {
            Log.d(TAG , "Session is inactive")
        }
        return null
    }

    override fun delete(entity: Session) {
    }

    override fun findAll(): List<Session>? {

        return null
    }

    override fun deleteAll() {
        editor!!.clear()
        editor!!.commit()
    }


}