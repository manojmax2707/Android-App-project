package com.example.listactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listactivity.databinding.ActivityUserBinding


class UserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val country = intent.getStringExtra("country")

        binding.name.text = name
        binding.phoneNo.text = phone
        binding.country.text = country

    }
}






