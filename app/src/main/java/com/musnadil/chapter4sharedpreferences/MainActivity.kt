package com.musnadil.chapter4sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.musnadil.chapter4sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val ID = "ID_KEY"
        const val NAME = "NAME_KEY"
    }

    private lateinit var binding: ActivityMainBinding
    val spf = "sharedPreferences"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(spf, MODE_PRIVATE)

        binding.btnSave.setOnClickListener {
            if (binding.etId.text.isNullOrEmpty()) {
                binding.etId.error = "anda harus memasukan id"
//                Toast.makeText(this, "anda harus memasukan id", Toast.LENGTH_SHORT).show()
            } else {
                val id: Int = Integer.parseInt(binding.etId.text.toString())
                val name = binding.etNama.text.toString()
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putInt(ID, id)
                editor.putString(NAME, name)
                editor.apply()
                binding.etId.text = null
                binding.etNama.text = null
                Toast.makeText(this, "Data telah disimpan", Toast.LENGTH_SHORT).show()

                val sharedIdValue = sharedPreferences.getInt(ID, 0)
                val sharedNameValue = sharedPreferences.getString(NAME, "default name")

                binding.tvId.text = sharedIdValue.toString()
                binding.tvNama.text = sharedNameValue.toString()
                Toast.makeText(this, "${sharedNameValue} ada dalam penyimpanan", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnView.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt(ID, 0)
            val sharedNameValue = sharedPreferences.getString(NAME, "default name")

            if (sharedIdValue.equals(0) && sharedNameValue.equals("default name")) {
                binding.tvId.text = sharedIdValue.toString()
                binding.tvNama.text = sharedNameValue.toString()
                Toast.makeText(this, "data kosong", Toast.LENGTH_SHORT).show()
            } else {
                binding.tvId.text = sharedIdValue.toString()
                binding.tvNama.text = sharedNameValue.toString()
                Toast.makeText(this, "${sharedNameValue} ada dalam penyimpanan", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.btnClear.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            binding.tvId.text = ""
            binding.tvNama.text = ""
        }
    }
}