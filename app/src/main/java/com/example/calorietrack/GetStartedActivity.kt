package com.example.calorietrack

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.calorietrack.databinding.ActivityGetStartedBinding

class GetStartedActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGetStartedBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("CalorieTrackData", Context.MODE_PRIVATE)

        var beratBadanUnit = ""
        var targetBeratBadanUnit = ""
        var selectedTujuanDiet = ""
        var selectedDate =""

        val spinnerUnitBeratBadan = binding.spinnerBb
        val spinnerUnitTargetBeratBadan = binding.spinnerTargetBB
        val spinnerTujuanDiet = binding.spinnerTujuanDiet

        val unitBeratAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.weight_units,
            android.R.layout.simple_spinner_item
        )

        val tujuanDietAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.tujuan_diet,
            android.R.layout.simple_spinner_item
        )

        unitBeratAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tujuanDietAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerUnitBeratBadan.adapter = unitBeratAdapter
        spinnerUnitTargetBeratBadan.adapter = unitBeratAdapter
        spinnerTujuanDiet.adapter = tujuanDietAdapter

        spinnerUnitBeratBadan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val selectedUnit = spinnerUnitBeratBadan.selectedItem.toString()
                beratBadanUnit = selectedUnit
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        spinnerUnitTargetBeratBadan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val selectedUnit = spinnerUnitTargetBeratBadan.selectedItem.toString()
                targetBeratBadanUnit = selectedUnit
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        spinnerTujuanDiet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                selectedTujuanDiet = spinnerTujuanDiet.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val datePicker = binding.datePicker
        val calendar = java.util.Calendar.getInstance()
        val currentYear = calendar.get(java.util.Calendar.YEAR)
        val currentMonth = calendar.get(java.util.Calendar.MONTH)
        val currentDay = calendar.get(java.util.Calendar.DAY_OF_MONTH)

        val monthNames = arrayOf(
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        )

        datePicker.init(currentYear, currentMonth, currentDay,
            DatePicker.OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
                selectedDate = "$dayOfMonth ${monthNames[monthOfYear]} $year"
            }
        )

        binding.btnGetstarted.setOnClickListener {
            val nama = binding.editTxtNama.text.toString()
            val beratBadan = binding.etBeratbadan.text.toString()
            val targetBeratBadan = binding.etTargetBB.text.toString()
            val targetKalori = binding.etKaloritarget.text.toString()

            if (nama.isNotEmpty() && beratBadan.isNotEmpty() && targetBeratBadan.isNotEmpty() && targetKalori.isNotEmpty()) {
                val editor = sharedPreferences.edit()
                editor.putString("nama", binding.editTxtNama.text.toString())
                editor.putString("beratBadan", "${binding.etBeratbadan.text} $beratBadanUnit")
                editor.putString("targetBeratBadan", "${binding.etTargetBB.text} $targetBeratBadanUnit")
                editor.putString("targetKalori", binding.etKaloritarget.text.toString())
                editor.putString("sisaKalori", binding.etKaloritarget.text.toString())
                editor.putString("tujuanDiet", selectedTujuanDiet)
                editor.putString("tanggalTarget", selectedDate)
                editor.putString("kaloriInPagi", "")
                editor.putString("kaloriOutPagi", "")
                editor.putString("totalKaloriPagi", "")

                editor.putString("kaloriInSiang", "")
                editor.putString("kaloriOutSiang", "")
                editor.putString("totalKaloriSiang", "")

                editor.putString("kaloriInMalam", "")
                editor.putString("kaloriOutMalam", "")
                editor.putString("totalKaloriMalam", "")
                editor.apply()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Mohon isi semua kolom", Toast.LENGTH_SHORT).show()
            }
        }
    }
}