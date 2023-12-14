package com.goodness.introduce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class SignInActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_signin)

		val email = findViewById<EditText>(R.id.et_email).text
		val password = findViewById<EditText>(R.id.et_password).text

		findViewById<Button>(R.id.btn_signin).setOnClickListener {
			if (email.isNotEmpty() && password.isNotEmpty()) {
				val intent = Intent(this, HomeActivity::class.java)

				intent.putExtra("email", email)
				intent.putExtra("password", password)

				startActivity(intent)
			}
		}
	}
}