package com.goodness.introduce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home)

		val email = intent.getStringExtra("email")
		val emailTextView = findViewById<TextView>(R.id.txt_email)

		email?.let { emailTextView.text = it }

		findViewById<Button>(R.id.btn_finish).setOnClickListener {
			finish()
		}
	}
}