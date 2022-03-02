package com.example.connect_firebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.connect_firebase.databinding.ActivityMainBinding.inflate
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

     private lateinit var binding: com.example.connect_firebase.databinding.ActivityMainBinding
     private lateinit var database: DatabaseReference
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = inflate(layoutInflater)
         setContentView(binding.root)

         binding.clickMe.setOnClickListener {

             val empNo = binding.editTextTextPersonName9.text.toString()
             val empName = binding.editTextTextPersonName10.text.toString()
             val empSal = binding.editTextTextPersonName11.text.toString()


             database = FirebaseDatabase.getInstance().getReference("Employees")
             val User = User(empNo, empName, empSal)
             database.push().child(System.currentTimeMillis().toString()).setValue(User).addOnSuccessListener {

                 binding.editTextTextPersonName9.text.clear()
                 binding.editTextTextPersonName10.text.clear()
                 binding.editTextTextPersonName11.text.clear()


                 Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

             }.addOnFailureListener {

                 Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()


             }

         }
     }

 }
