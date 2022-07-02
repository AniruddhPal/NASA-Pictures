package com.obiviousdemo.utils

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class Helper {


    companion object {
        var gson = Gson()
        fun getJsonFromAssets(context: Context, fileName: String?): String? {
            val jsonString: String?
            jsonString = try {
                val `is`: InputStream = context.getAssets().open(fileName!!)
                val size: Int = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer, charset("UTF-8"))
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
            return jsonString
        }

        fun convertPojoToJson(pojo: Any?): String? {
            return gson.toJson(pojo)
        }

        fun convertJSONtoPOJO(jsonAsString: String?, objectType: Class<*>?): Any? {
            return gson.fromJson<Any>(
                jsonAsString,
                objectType
            )
        }
    }


}