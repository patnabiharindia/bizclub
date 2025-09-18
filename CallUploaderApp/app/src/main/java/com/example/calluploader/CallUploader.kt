package com.example.calluploader

import okhttp3.*
import org.json.JSONObject
import java.io.IOException

object CallUploader {
    private const val API_KEY = "CHANGE_ME_TO_A_SECRET"
    private const val ENDPOINT =
        "https://script.google.com/macros/s/AKfycbxYourScriptID/exec"

    private val client = OkHttpClient()

    fun uploadCall(
        deviceUuid: String,
        number: String,
        callType: String,
        startTime: Long,
        duration: Int,
        lat: Double?,
        lon: Double?,
        notes: String?
    ) {
        val json = JSONObject().apply {
            put("api_key", API_KEY)
            put("device_uuid", deviceUuid)
            put("phone_number", number)
            put("call_type", callType)
            put("start_time", startTime)
            put("duration_seconds", duration)
            if (lat != null) put("latitude", lat)
            if (lon != null) put("longitude", lon)
            if (notes != null) put("notes", notes)
        }

        val body = RequestBody.create(
            MediaType.parse("application/json"),
            json.toString()
        )

        val req = Request.Builder()
            .url(ENDPOINT)
            .post(body)
            .build()

        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                println("Upload result: ${response.body()?.string()}")
            }
        })
    }
}