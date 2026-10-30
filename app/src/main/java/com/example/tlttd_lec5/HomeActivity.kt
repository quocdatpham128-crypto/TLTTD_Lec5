package com.example.tlttd_lec5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.tlttd_lec5.databinding.ActivityHomeBinding
import com.example.tlttd_lec5.model.User

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = IntentCompat.getSerializableExtra(intent, "USER_DATA", User::class.java)

        user?.let {
            binding.tvHeaderName.text = it.name
            binding.tvEmail.text = it.email
            binding.tvPhone.text = it.phone
            binding.tvDob.text = it.dob
            binding.tvGender.text = it.gender
            binding.tvAddress.text = it.address
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}