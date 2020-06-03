package com.example.timefighter.timefighter.enja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeBtn: Button
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var gameTimerTextView: TextView
    internal lateinit var countDownTimer: CountDownTimer

    internal var score = 0
    internal var gameStarted = false;
    internal val initialCountDown: Long = 60000 // one minute countdown
    internal val countdownInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeBtn = findViewById(R.id.tapBtn)
        gameScoreTextView = findViewById(R.id.gameScoreTextView)
        gameTimerTextView = findViewById((R.id.gameTimerTextView))

        tapMeBtn.setOnClickListener { view ->
            incrementScore()
        }

        resetGame()
    }

    private fun resetGame() {
        score = 0
        gameScoreTextView.text = getString(R.string.yourScore, score)
        val initialTimeLeft = initialCountDown / 1000
        gameTimerTextView.text = getString(R.string.timeLeft, initialTimeLeft)

        countDownTimer = object : CountDownTimer(initialCountDown, countdownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                gameTimerTextView.text = getString(R.string.timeLeft, timeLeft)
            }

            override fun onFinish() {
                endGame()
            }
        }

        gameStarted = false
    }

    private fun incrementScore() {
        if (!gameStarted) {
            startGame()
        }

        score += 1
        val newScore = getString(R.string.yourScore, score)
        gameScoreTextView.text = newScore
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        Toast.makeText(this, getString(R.string.gameOverMessage, score), Toast.LENGTH_LONG).show()
        resetGame()
    }
}
