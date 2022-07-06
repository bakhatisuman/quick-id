package com.example.mycameraapp.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Type
import java.util.HashMap

object GsonUtils {

    var TAG = GsonUtils::class.java.simpleName

    fun <T> toObject(data: String, type: Class<T>): T {
        var gson = Gson()
        return gson.fromJson(data, type)
    }



    fun toString(src: Any?): String? {
        if (src == null) {
            return null
        }
        var builder = GsonBuilder()
        builder.setPrettyPrinting()
        var gson = builder.create()
        return gson.toJson(src)
    }


    fun <T> toObject(data: String?, type: Type): T? {
        try {
            var gson = Gson()
            return gson.fromJson<T>(data, type)
        } catch (ex: Exception) {
            return null
        }

    }


    fun getJSONObject(src: Any): JSONObject? {
        val data = toString(src)

        try {
            return JSONObject(data)
        } catch (e: JSONException) {
            Log.e(TAG,e.message!!)
        }

        return null
    }


    fun getJSONObject(data: String): JSONObject? {
        try {
            return JSONObject(data)
        } catch (e: JSONException) {
            Log.e(TAG,e.message!!)
        }

        return null
    }


    fun getHashMap(src: Any): HashMap<String, String>? {
        val data = toString(src)
        return toObject<HashMap<String, String>>(data, object : TypeToken<HashMap<String, String>>() {

        }.type)
    }


}