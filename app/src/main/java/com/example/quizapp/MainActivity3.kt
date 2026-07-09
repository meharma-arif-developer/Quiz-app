package com.example.quizapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure this layout name matches your XML file exactly
        setContentView(R.layout.activity_main3)

        val tvScore = findViewById<TextView>(R.id.tvFinalScore)
        val tvAnswers = findViewById<TextView>(R.id.tvAnswers)

        // Data receive kar rahe hain QuizActivity se
        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 0)
        val answers = intent.getStringExtra("ANSWERS") ?: "No answers available."

        // UI update kar rahe hain
        tvScore.text = "Final Score: $score / $total"
        tvAnswers.text = answers
    }
}
