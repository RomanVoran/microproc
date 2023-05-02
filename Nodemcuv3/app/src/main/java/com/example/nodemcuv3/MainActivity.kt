package com.example.nodemcuv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var btnOn: Button
    private lateinit var btnOff: Button
    private lateinit var btbUpdate: Button
    private lateinit var ledStatusText: TextView
    private lateinit var responseStatusText: TextView
    private lateinit var progressBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOn = findViewById(R.id.button_off)
        btnOff = findViewById(R.id.button_on)
        btbUpdate = findViewById(R.id.button_status)
        progressBar = findViewById(R.id.progress_bar)
        ledStatusText = findViewById(R.id.led_status)
        responseStatusText = findViewById(R.id.response_status)


        send("/?status")
        btnOn.setOnClickListener { send("/?led=on") }
        btnOff.setOnClickListener { send("/?led=off") }
        btbUpdate.setOnClickListener { send("/?status") }
    }

    private fun disableUi() = lifecycleScope.launch(Dispatchers.Main) {
        progressBar.visibility = View.VISIBLE
        btnOn.isEnabled = false
        btnOff.isEnabled = false
    }

    private fun enableUi() = lifecycleScope.launch(Dispatchers.Main) {
        progressBar.visibility = View.GONE
        btnOn.isEnabled = true
        btnOff.isEnabled = true
    }

    private fun setResponseText(rawMessage: String) =
        setResponseText(Gson().fromJson(rawMessage, Message::class.java))


    private fun setResponseText(message: Message) = lifecycleScope.launch(Dispatchers.Main) {
        ledStatusText.text = "Led status: ${message.ledStatus}"
        responseStatusText.text = "Response status: ${message.responseStatus}"
        Log.d("TEST_TAG", "message = $message")
    }

    private fun send(requestBody: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            disableUi()
            try {
                val marketUrl = "http://192.168.1.151${requestBody}"
                val client =
                    OkHttpClient.Builder().connectTimeout(4000, TimeUnit.MILLISECONDS).build()
                val request = Request.Builder().url(marketUrl).build()
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    setResponseText(response.message)
                }
                Log.d("TEST_TAG", "response = ${response.message}")
            } catch (e: Exception) {
                setResponseText(Message("Failure", "Unknown"))
                Log.w("NetWorkRepository", "isAppAvailableOnGooglePlay error", e)
            }
            enableUi()
        }
    }
}

