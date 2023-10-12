package com.example.calorietrack

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calorietrack.databinding.ActivityHomeBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
        val formattedDate = dateFormat.format(calendar.time)
        binding.txtTanggal.text = formattedDate

        // Retrieve data from SharedPreferences
        val nama = sharedPreferences.getString("nama", "")
        val beratBadan = sharedPreferences.getString("beratBadan", "")
        val targetBeratBadan = sharedPreferences.getString("targetBeratBadan", "")
        val targetKalori = sharedPreferences.getString("targetKalori", "")
        val sisaKalori = sharedPreferences.getString("sisaKalori", "")
        val tujuanDiet = sharedPreferences.getString("tujuanDiet", "")
        val tanggalTarget = sharedPreferences.getString("tanggalTarget", "")

        val kaloriInPagi = sharedPreferences.getString("kaloriInPagi", "")
        val kaloriOutPagi = sharedPreferences.getString("kaloriOutPagi", "")
        val totalKaloriPagi = sharedPreferences.getString("totalKaloriPagi", "")

        val kaloriInSiang = sharedPreferences.getString("kaloriInSiang", "")
        val kaloriOutSiang = sharedPreferences.getString("kaloriOutSiang", "")
        val totalKaloriSiang = sharedPreferences.getString("totalKaloriSiang", "")

        val kaloriInMalam = sharedPreferences.getString("kaloriInMalam", "")
        val kaloriOutMalam = sharedPreferences.getString("kaloriOutMalam", "")
        val totalKaloriMalam = sharedPreferences.getString("totalKaloriMalam", "")

        // Display the retrieved data
        binding.txtNama.text = nama
        binding.txtBeratBadan.text = beratBadan
        binding.txtTujuanDiet.text = tujuanDiet
        binding.txtTanggalTarget.text = tanggalTarget
        binding.txtTargetBeratBadan.text = targetBeratBadan
        binding.txtTargetKalori.text = targetKalori

        binding.txtSisaKalori.text = sisaKalori

        binding.txtMakanPagi.text = kaloriInPagi
        binding.txtWorkoutPagi.text = kaloriOutPagi
        binding.txtKaloriPagi.text = totalKaloriPagi

        binding.txtMakanSiang.text = kaloriInSiang
        binding.txtWorkoutSiang.text = kaloriOutSiang
        binding.txtKaloriSiang.text = totalKaloriSiang

        binding.txtMakanMalam.text = kaloriInMalam
        binding.txtWorkoutMalam.text = kaloriOutMalam
        binding.txtKaloriMalam.text = totalKaloriMalam

        binding.btnInputData.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }
    }
}