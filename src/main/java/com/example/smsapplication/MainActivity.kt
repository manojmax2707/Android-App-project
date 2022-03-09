package com.example.smsapplication

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var editTextNumber: EditText
    lateinit var editTextMessage: EditText
    private val permissionRequest = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "SMS App"
        editTextNumber = findViewById(R.id.editTextNum)
        editTextMessage = findViewById(R.id.editTextMsg)
        button = findViewById(R.id.btnSendMsg)

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.RECEIVE_SMS,
                Manifest.permission.SEND_SMS),
                permissionRequest
            )
        }
        else
            receiveMsg()

        button.setOnClickListener {
            var sms = SmsManager.getDefault()
            sms.sendTextMessage(editTextNumber.text.toString(), "ME", editTextMessage.text.toString(), null, null)
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults:
    IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionRequest) {
            if (requestCode==101 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                receiveMsg()
            } else {
                Toast.makeText(this, "You don't have required permission to send a message",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun receiveMsg() {
        var bc = object: BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {

                for(sms in Telephony.Sms.Intents.getMessagesFromIntent(p1)){
                    editTextNumber.setText(sms.originatingAddress)
                    editTextMessage.setText(sms.displayMessageBody)
                    Toast.makeText(
                        applicationContext,
                        sms.displayMessageBody,Toast.LENGTH_LONG).show()

                }

            }

        }
        registerReceiver(bc, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
    }


}




