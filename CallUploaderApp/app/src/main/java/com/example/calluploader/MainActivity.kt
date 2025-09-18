package com.example.calluploader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnUpload = findViewById<Button>(R.id.btnUpload)

        btnUpload.setOnClickListener {
            // Example upload
            CallUploader.uploadCall(
                deviceUuid = "test-device-123",
                number = "+911234567890",
                callType = "OUTGOING",
                startTime = System.currentTimeMillis() / 1000,
                duration = 45,
                lat = null,
                lon = null,
                notes = "Test from app"
            )
            Toast.makeText(this, "Upload triggered", Toast.LENGTH_SHORT).show()
        }
    }
}