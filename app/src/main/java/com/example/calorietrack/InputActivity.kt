package com.example.calorietrack

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.calorietrack.databinding.ActivityInputBinding
import java.util.Calendar

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    private lateinit var sharedPreferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val spinnerWaktuKaloriIn = binding.spinnerWaktuKaloriIn
        val waktuKaloriInAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.waktu_makan,
            android.R.layout.simple_spinner_item
        )
        waktuKaloriInAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerWaktuKaloriIn.adapter = waktuKaloriInAdapter

        val timePicker = binding.timepicker

        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        timePicker.setIs24HourView(true)
        timePicker.hour = currentHour
        timePicker.minute = currentMinute

        timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            // Handle the selected time (hourOfDay and minute) here
        }

        binding.btnSimpan.setOnClickListener {
            val kaloriInText = binding.editTextJumlahKaloriIn.text.toString()
            val kaloriOutText = binding.editTextKaloriTerbakar.text.toString()
            val namaMakanan = binding.editTextNamaMakanan.text.toString()
            val namaWorkout = binding.editTextNamaWorkout.text.toString()

            if (kaloriInText.isNotEmpty() && kaloriOutText.isNotEmpty() && namaMakanan.isNotEmpty() && namaWorkout.isNotEmpty()) {
                val kaloriIn = kaloriInText.toDouble()
                val kaloriOut = kaloriOutText.toDouble()

                val totalKalori = kaloriIn - kaloriOut
                val targetKalori = sharedPreferences.getString("sisaKalori", "0")?.toDouble() ?: 0.0

                val newTargetKalori = targetKalori - totalKalori

                editor.putString("sisaKalori", newTargetKalori.toString())

                val dataKaloriIn = "$namaMakanan - $kaloriIn kal"
                val datakaloriOut = "$namaWorkout - $kaloriOut kal"

                val position = spinnerWaktuKaloriIn.selectedItemPosition
                when (position) {
                    0 -> {
                        editor.putString("kaloriInPagi", dataKaloriIn)
                        editor.putString("kaloriOutPagi", datakaloriOut)
                        editor.putString("totalKaloriPagi", "$totalKalori kal")
                    }
                    1 -> {
                        editor.putString("kaloriInSiang", dataKaloriIn)
                        editor.putString("kaloriOutSiang", datakaloriOut)
                        editor.putString("totalKaloriSiang", "$totalKalori kal")
                    }
                    2 -> {
                        editor.putString("kaloriInMalam", dataKaloriIn)
                        editor.putString("kaloriOutMalam", datakaloriOut)
                        editor.putString("totalKaloriMalam", "$totalKalori kal")
                    }
                }
                editor.apply()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Mohon isi kedua kalori in dan out", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnKembali.setOnClickListener {
            onBackPressed()
        }
    }
}