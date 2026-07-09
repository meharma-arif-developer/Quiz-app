package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.student_name)
        val etRollNo = findViewById<EditText>(R.id.roll_number)
        val btnStart = findViewById<Button>(R.id.start_button)

        btnStart.setOnClickListener {
            val name = etName.text.toString().trim()
            val roll = etRollNo.text.toString().trim()

            if (name.isNotEmpty() && roll.isNotEmpty()) {

                // 👉 Direct screen open (NO data send)
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Please enter Name and Roll No", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
