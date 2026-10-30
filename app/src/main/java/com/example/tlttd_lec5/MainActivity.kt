package com.example.tlttd_lec5

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.tlttd_lec5.databinding.ActivityMainBinding
import com.example.tlttd_lec5.model.User
import com.example.tlttd_lec5.validator.FormValidator
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRealtimeValidation()
        setupListeners()
    }

    private fun setupRealtimeValidation() {
        binding.etName.doAfterTextChanged { binding.tilName.error = null }
        binding.etEmail.doAfterTextChanged { binding.tilEmail.error = null }
        binding.etPassword.doAfterTextChanged { binding.tilPassword.error = null }
        binding.etPhone.doAfterTextChanged { binding.tilPhone.error = null }
        binding.etDob.doAfterTextChanged { binding.tilDob.error = null }
        binding.etAddress.doAfterTextChanged { binding.tilAddress.error = null }
    }

    private fun setupListeners() {
        // DatePicker dialog khi nhấn vào trường Ngày sinh
        binding.etDob.setOnClickListener {
            showDatePickerDialog()
        }

        // Nút Huỷ -> Xóa trắng các trường để nhập lại
        binding.btnCancel.setOnClickListener {
            clearForm()
        }

        // Nút Đăng ký -> Validate form
        binding.btnSubmit.setOnClickListener {
            if (validateForm()) {
                val name = binding.etName.text.toString().trim()
                val email = binding.etEmail.text.toString().trim()
                val phone = binding.etPhone.text.toString().trim()
                val dob = binding.etDob.text.toString().trim()
                val gender = getSelectedGender()
                val address = binding.etAddress.text.toString().trim()

                val user = User(name, email, phone, dob, gender, address)

                // Success -> Chuyển sang màn hình trang chủ hiển thị thông tin
                val intent = Intent(this, HomeActivity::class.java).apply {
                    putExtra("USER_DATA", user)
                }
                startActivity(intent)
            } else {
                // Fail -> Hiện Snackbar báo lỗi
                Snackbar.make(binding.mainLayout, getString(R.string.err_form_fail), Snackbar.LENGTH_LONG)
                    .setAction("ĐÓNG") { }
                    .show()
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                binding.etDob.setText(formattedDate)
            },
            year,
            month,
            day,
        )
        datePickerDialog.show()
    }

    private fun getSelectedGender(): String {
        return when (binding.rgGender.checkedRadioButtonId) {
            binding.rbFemale.id -> getString(R.string.gender_female)
            binding.rbOther.id -> getString(R.string.gender_other)
            else -> getString(R.string.gender_male)
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        val name = binding.etName.text.toString()
        if (!FormValidator.isValidName(name)) {
            binding.tilName.error = getString(R.string.err_empty_name)
            isValid = false
        }

        val email = binding.etEmail.text.toString()
        if (!FormValidator.isValidEmail(email)) {
            binding.tilEmail.error = getString(R.string.err_invalid_email)
            isValid = false
        }

        val pass = binding.etPassword.text.toString()
        if (!FormValidator.isValidPassword(pass)) {
            binding.tilPassword.error = getString(R.string.err_short_pass)
            isValid = false
        }

        val phone = binding.etPhone.text.toString()
        if (!FormValidator.isValidPhone(phone)) {
            binding.tilPhone.error = getString(R.string.err_invalid_phone)
            isValid = false
        }

        val dob = binding.etDob.text.toString()
        if (!FormValidator.isValidDob(dob)) {
            binding.tilDob.error = getString(R.string.err_empty_dob)
            isValid = false
        }

        val address = binding.etAddress.text.toString()
        if (!FormValidator.isValidAddress(address)) {
            binding.tilAddress.error = getString(R.string.err_empty_address)
            isValid = false
        }

        return isValid
    }

    private fun clearForm() {
        binding.etName.text?.clear()
        binding.etEmail.text?.clear()
        binding.etPassword.text?.clear()
        binding.etPhone.text?.clear()
        binding.etDob.text?.clear()
        binding.etAddress.text?.clear()

        binding.rbMale.isChecked = true

        binding.tilName.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilPhone.error = null
        binding.tilDob.error = null
        binding.tilAddress.error = null

        binding.etName.requestFocus()
    }
}