package com.goodness.introduce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignInActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_signin)

		val emailEditView = findViewById<EditText>(R.id.et_email)
		val passwordEditView = findViewById<EditText>(R.id.et_password)

		findViewById<Button>(R.id.btn_signin).setOnClickListener {
			val email = emailEditView.text.toString()
			val password = passwordEditView.text.toString()

			if (email.trim().isNotEmpty() && password.trim().isNotEmpty()) {
				val intent = Intent(this, HomeActivity::class.java)

				intent.putExtra("email", email)
				startActivity(intent)
				Toast.makeText(this, getString(R.string.toast_msg_signin_success), Toast.LENGTH_SHORT).show()
			} else {
				Toast.makeText(this, getString(R.string.toast_msg_signup_fail), Toast.LENGTH_SHORT).show()
			}
		}

		findViewById<Button>(R.id.btn_signup).setOnClickListener {
			val intent = Intent(this, SignUpActivity::class.java)

			startActivity(intent)
		}
	}
}