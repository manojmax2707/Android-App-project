package com.example.getdatafromfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class EmpListActivity : AppCompatActivity() {

    private lateinit var dbRef : DatabaseReference
    private lateinit var empRecyclerView: RecyclerView
    private lateinit var empArrayList: ArrayList<emp>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp_list)

        empRecyclerView = findViewById(R.id.empList)
        empRecyclerView.layoutManager = LinearLayoutManager(this)
        empRecyclerView.setHasFixedSize(true)

        empArrayList = arrayListOf<emp>()
        getUserdata()
    }

    private fun getUserdata() {

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        dbRef.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){

                    for(userSnapshot in snapshot.children){

                        val emp = userSnapshot.getValue(emp::class.java)
                        empArrayList.add(emp!!)
                    }

                    empRecyclerView.adapter = MyAdapter(empArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
}