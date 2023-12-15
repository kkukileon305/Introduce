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


		findViewById<Button>(R.id.btn_signin).setOnClickListener {
			val email = findViewById<EditText>(R.id.et_email).text.toString()
			val password = findViewById<EditText>(R.id.et_password).text.toString()

			if (email.isNotEmpty() && password.isNotEmpty()) {
				val intent = Intent(this, HomeActivity::class.java)

				intent.putExtra("email", email)
				startActivity(intent)
			} else {
				Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
			}
		}

		findViewById<Button>(R.id.btn_signup).setOnClickListener {
			val intent = Intent(this, SignUpActivity::class.java)

			startActivity(intent)
		}
	}
}