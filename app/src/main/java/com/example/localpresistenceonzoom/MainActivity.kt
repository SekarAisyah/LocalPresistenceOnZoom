package com.example.localpresistenceonzoom

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.localpresistenceonzoom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("dataprefs", Context.MODE_PRIVATE)

        //Untuk Menyimpan Data ke Shared Preferences
        saveData()

        //Untuk Menampilkan Data
        viewData()

        //Untuk Menghapus Data
        clearData()
    }

    fun saveData(){
        binding.btnSave.setOnClickListener {

            //Untuk mengambil isi dari edittext
            val name = binding.etInputName.text.toString()
            val id = binding.etInputId.text.toString()


            //Simpan data ke preference
            val save = sharedPref.edit()
            save.putString("namauser", name)
            save.putString("iduser", id)
            save.apply()

            //Untuk Memunculkan Toast
            Toast.makeText(this, "Berhasil Simpan Data", Toast.LENGTH_LONG).show()
        }
    }

    fun viewData(){

        binding.btnView.setOnClickListener {
            val getNama = sharedPref.getString("namauser", "")
            val getId = sharedPref.getString("iduser", "")

            //Set Data dari sharedPreference ke TextView
            binding.tvLabelId.text = getId
            binding.tvLabelName.text = getNama
        }
    }

    fun clearData(){
        binding.btnClear.setOnClickListener {
            val hapus = sharedPref.edit()
            hapus.clear()
            hapus.apply()
            binding.tvLabelId.setText("")
            binding.tvLabelName.setText("")
        }
    }
}