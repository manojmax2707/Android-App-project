package com.example.listactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listactivity.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userArrayList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageId = intArrayOf(


        )

        val name = arrayOf(

            "Will Smith",
            "Ronaldo",
            "Messi",
            "Beckham",
            "Rooney",
            "CR7"
        )

        val lastMessage = arrayOf(

            "Next Movie?",
            "Let's  play Soccer!",
            "How is Paris?",
            "Will Miami Fc win?",
            "My twin brother",
            "Leave ManU"

        )

        val lastMsgTime = arrayOf(

            "8:00 pm",
            "7:00 am",
            "6:45 pm",
            "12:30 pm",
            "11:00 pm",
            "5:00 pm",

        )

        val phoneNo = arrayOf( "9999000123",
            "8888000123",
            "7777000123",
            "6666000123",
            "5555000123",
            "4444000123",
        )

        val hobby = arrayOf(
            "Acting",
            "Party",
            "Soccer",
            "Touring",
            "Boxing",
            "Diet",
        )

        val country = arrayOf(
            "USA",
            "Brazil",
            "Argentina",
            "England",
            "England",
            "Portugal"

        )

        userArrayList = ArrayList()

        for(i in name.indices){

            val user = User(name[i],lastMessage[i],lastMsgTime[i],phoneNo[i],hobby[i],country[i], imageId[i])
            userArrayList.add(user)
        }

    }
}


