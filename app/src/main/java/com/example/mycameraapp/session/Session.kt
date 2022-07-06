package com.example.mycameraapp.session

import java.io.Serializable

class Session : Serializable {

    companion object {
        val TAG = SessionManager::class.java.simpleName
    }

    var user: User? = null

    // var profile : Profile? = null
    var isSessionActive: Boolean = false

}