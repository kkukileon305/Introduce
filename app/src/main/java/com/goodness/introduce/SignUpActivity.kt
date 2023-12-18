package com.goodness.introduce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class SignUpActivity : AppCompatActivity() {
	private val nameWarnTextView by lazy { findViewById<TextView>(R.id.warn_name) }
	private val emailWarnTextView by lazy { findViewById<TextView>(R.id.warn_email) }
	private val passwordWarnTextView by lazy { findViewById<TextView>(R.id.warn_password) }

	private val nameEditView by lazy { findViewById<EditText>(R.id.et_name) }
	private val emailEditView by lazy { findViewById<EditText>(R.id.et_email) }
	private val passwordEditView by lazy { findViewById<EditText>(R.id.et_password) }

	private val spinnerView by lazy { findViewById<Spinner>(R.id.sp_email) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_signup)

		initView()
	}

	private fun initView() {
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

		emailEditView.setOnFocusChangeListener { v, hasFocus ->
			if (!hasFocus && v is EditText) {

				val email = v.text.toString()
				emailWarnTextView.text = if (isEmailValid(email)) "" else "유효한 이메일을 입력해주세요."
			}
		}

		//TODO Password Validation


		val emailList = listOf(
			EmailData(1, "google.com"),
			EmailData(2, "naver.com"),
			EmailData(3, "직접입력")
		)
		val spinnerAdapter = EmailAdapter(this, emailList)

		spinnerView.adapter = spinnerAdapter

		spinnerView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
				TODO("Not yet implemented")
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {
				TODO("Not yet implemented")
			}
		}
	}

	private fun isEmailValid(email: String): Boolean {
		return Patterns.EMAIL_ADDRESS.matcher(email).matches()
	}
}