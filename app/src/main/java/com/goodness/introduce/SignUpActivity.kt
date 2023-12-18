package com.goodness.introduce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class SignUpActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_signup)

		val nameWarnTextView = findViewById<TextView>(R.id.warn_name)
		val emailWarnTextView = findViewById<TextView>(R.id.warn_email)
		val passwordWarnTextView = findViewById<TextView>(R.id.warn_password)

		val nameEditView = findViewById<EditText>(R.id.et_name)
		val emailEditView = findViewById<EditText>(R.id.et_email)
		val passwordEditView = findViewById<EditText>(R.id.et_password)

		findViewById<Button>(R.id.btn_signup).setOnClickListener {
			val name = nameEditView.text.toString()
			val email = emailEditView.text.toString()
			val password = passwordEditView.text.toString()

			if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
				finish()
			} else {
				Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
			}
		}

		nameEditView.setOnFocusChangeListener { v, hasFocus ->
			if (!hasFocus && v is EditText) {
				val name = v.text.toString()
				nameWarnTextView.text = if (name.isEmpty()) "이름을 입력해주세요." else ""
			}
		}

		//TODO Email Validation
		//TODO Password Validation
	}

	fun isEmailValid(email: String): Boolean {
		return Patterns.EMAIL_ADDRESS.matcher(email).matches()
	}
}