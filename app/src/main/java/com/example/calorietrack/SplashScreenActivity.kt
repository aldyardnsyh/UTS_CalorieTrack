package com.example.calorietrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calorietrack.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val splashDuration = 1500

        binding.root.postDelayed({
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }, splashDuration.toLong())
    }
}