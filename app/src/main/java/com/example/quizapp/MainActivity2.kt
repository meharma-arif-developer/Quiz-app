package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {

    private val questions = arrayOf(
        "Software Engineering is a systematic approach?",
        "MySQL is a NoSQL database?",
        "PHP is used for server-side scripting?",
        "HTML is a programming language?",
        "Arduino can use ultrasonic sensors for distance?",
        "Agile is a requirement engineering model?",
        "Spiral model is good for large projects?",
        "XAMPP uses port 3306 for MySQL by default?",
        "Python is developed by Microsoft?",
        "WordPress can be installed on localhost?"
    )

    private val answers = arrayOf(true, false, true, false, true, true, true, true, false, true)

    private var currentIndex = 0
    private var score = 0
    private var quizTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvQuestion = findViewById<TextView>(R.id.question_text)
        val tvTimerText = findViewById<TextView>(R.id.timer_text)
        val progressBar = findViewById<ProgressBar>(R.id.quiz_progress)

        val btnTrue = findViewById<Button>(R.id.true_button)
        val btnFalse = findViewById<Button>(R.id.false_button)
        val btnNext = findViewById<ImageButton>(R.id.next_button)
        val btnEnd = findViewById<Button>(R.id.end_quiz_button)

        progressBar.max = questions.size
        updateUI(tvQuestion, progressBar, tvScore)

        // TIMER
        quizTimer = object : CountDownTimer(600000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                val sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                tvTimerText.text = String.format("Time Left: %02d:%02d", min, sec)
            }

            override fun onFinish() {
                finish()
            }
        }.start()

        // TRUE
        btnTrue.setOnClickListener {
            handleAnswer(true, tvQuestion, progressBar, tvScore)
        }

        // FALSE
        btnFalse.setOnClickListener {
            handleAnswer(false, tvQuestion, progressBar, tvScore)
        }

        // NEXT
        btnNext.setOnClickListener {
            if (currentIndex < questions.size - 1) {
                currentIndex++
                updateUI(tvQuestion, progressBar, tvScore)
            }
        }

        // ✅ END QUIZ → OPEN RESULT ACTIVITY
        btnEnd.setOnClickListener {

            quizTimer?.cancel()

            val resultText = StringBuilder()

            for (i in questions.indices) {
                val correct = if (answers[i]) "True" else "False"
                resultText.append("${i + 1}. ${questions[i]}\nCorrect Answer: $correct\n\n")
            }

            val intent = Intent(this@MainActivity2, MainActivity3::class.java)

            intent.putExtra("SCORE", score)
            intent.putExtra("TOTAL", questions.size)
            intent.putExtra("ANSWERS", resultText.toString())

            startActivity(intent)   // 🔥 CONNECTION MADE HERE
            finish()                // close quiz screen
        }
    }

    private fun handleAnswer(
        userSelect: Boolean,
        tvQ: TextView,
        pb: ProgressBar,
        tvS: TextView
    ) {
        if (userSelect == answers[currentIndex]) {
            score++
        }

        if (currentIndex < questions.size - 1) {
            currentIndex++
            updateUI(tvQ, pb, tvS)
        } else {
            findViewById<Button>(R.id.end_quiz_button).visibility = Button.VISIBLE
        }
    }

    private fun updateUI(
        tvQ: TextView,
        pb: ProgressBar,
        tvS: TextView
    ) {
        tvQ.text = questions[currentIndex]
        pb.progress = currentIndex + 1
        tvS.text = "Score: $score/${questions.size}"
    }

    override fun onDestroy() {
        super.onDestroy()
        quizTimer?.cancel()
    }
}
