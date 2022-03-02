package com.example.sms_send_receive_demo

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.provider.Telephony
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private lateinit var phone: EditText
    private lateinit var text: EditText
    private lateinit var send: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phone = findViewById(R.id.editText) // phone editText to enter phone number
        text = findViewById(R.id.editText2) // text editText2 to type text
        send = findViewById(R.id.button) // button to send the sms
        // first set up run time permissions

        checkPermission()

        send.setOnClickListener {
            val phoneNumber = phone.text.toString()
            if(phoneNumber.isNotEmpty()) {
                // now set up sms intent
                val smsIntent = Intent(Intent.ACTION_VIEW)
                smsIntent.data = Uri.parse("sms:$phoneNumber")
                startActivity(smsIntent)
            }
                send.setOnClickListener {
                    val smsMessage = text.text.toString()
                    if(smsMessage.isNotBlank()){
                        // now set up sms message intent
                        val smsIntent2 = Intent(Intent.ACTION_VIEW)
                        smsIntent2.data = Uri.parse("sms:$smsMessage")
                        startActivity(smsIntent2)
                }
            }

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECEIVE_SMS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS),
                    111
                )
            } else
                receiveMsg()
        }

        fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                receiveMsg()

        }

        fun receiveMsg() {

            val br = object : BroadcastReceiver() {

                override fun onReceive(p0: Context?, p1: Intent?) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        for (sms in Telephony.Sms.Intents.getMessagesFromIntent(p1)) {

                            val editText = (sms.originatingAddress)
                            val editText2 = (sms.displayMessageBody.toString())
                        }
                    }
                }
            }

            registerReceiver(br, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))

        }

    }

    private fun receiveMsg() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), 101)
        }
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 111)
        }
    }

}

