package com.example.calorietrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calorietrack.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
    }
}