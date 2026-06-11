package com.example.ttuaplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.ttuaplication.databinding.ActivityDetailAccountBinding

class DetailAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        Toast.makeText(this, R.string.msg_success, Toast.LENGTH_SHORT).show()

        val bundle = intent.extras
        if (bundle != null) {
            val nama = bundle.getString("EXTRA_NAMA")
            val nim = bundle.getString("EXTRA_NIM")
            val ipk = bundle.getString("EXTRA_IPK")

            binding.tvNama.text = "${getString(R.string.label_name)} $nama"
            binding.tvNim.text = "${getString(R.string.label_nim)} $nim"
            binding.tvIpk.text = "${getString(R.string.label_ipk)} $ipk"
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}