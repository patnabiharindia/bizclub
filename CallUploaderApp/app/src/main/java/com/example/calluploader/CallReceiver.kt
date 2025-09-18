package com.example.calluploader

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import java.util.*

class CallReceiver : BroadcastReceiver() {
    private var startTime: Long = 0
    private var number: String? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent == null) return

        val stateStr = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
        val phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)

        when (stateStr) {
            TelephonyManager.EXTRA_STATE_RINGING -> {
                number = phoneNumber
                startTime = System.currentTimeMillis() / 1000
            }
            TelephonyManager.EXTRA_STATE_OFFHOOK -> {
                // Call answered or outgoing started
                if (number == null) {
                    number = phoneNumber ?: "unknown"
                }
                startTime = System.currentTimeMillis() / 1000
            }
            TelephonyManager.EXTRA_STATE_IDLE -> {
                // Call ended
                val endTime = System.currentTimeMillis() / 1000
                val duration = (endTime - startTime).toInt()

                if (number != null && startTime != 0L) {
                    Log.d("CallReceiver", "Uploading call: $number, duration=$duration")

                    CallUploader.uploadCall(
                        deviceUuid = UUID.randomUUID().toString(),
                        number = number!!,
                        callType = "UNKNOWN",
                        startTime = startTime,
                        duration = duration,
                        lat = null,
                        lon = null,
                        notes = null
                    )
                }
                number = null
                startTime = 0
            }
        }
    }
}
