package com.goodness.introduce

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
	private val nameWarnTextView by lazy { findViewById<TextView>(R.id.warn_name) }
	private val emailWarnTextView by lazy { findViewById<TextView>(R.id.warn_email) }
	private val emailBodyWarnTextView by lazy { findViewById<TextView>(R.id.warn_email_body) }
	private val passwordWarnTextView by lazy { findViewById<TextView>(R.id.warn_password) }

	private val nameEditView by lazy { findViewById<EditText>(R.id.et_name) }
	private val emailEditView by lazy { findViewById<EditText>(R.id.et_email) }
	private val emailBodyEditView by lazy { findViewById<EditText>(R.id.et_email_body) }
	private val passwordEditView by lazy { findViewById<EditText>(R.id.et_password) }

	private var isNameValidation = false
	private var isEmailValidation = false
	private var isEmailBodyValidation = true
	private var isPasswordValidation = false

	private val spinnerView by lazy { findViewById<Spinner>(R.id.sp_email) }

	private val signupBtn by lazy { findViewById<Button>(R.id.btn_signup) }

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
				//	Focus Out 이벤트 없이 클릭하는 경우를 막음
				Toast.makeText(this, getString(R.string.signup_fail_msg), Toast.LENGTH_SHORT).show()
			}
		}

		val editsAndWarns = listOf(
			nameEditView to nameWarnTextView,
			emailEditView to emailWarnTextView,
			emailBodyEditView to emailBodyWarnTextView,
			passwordEditView to passwordWarnTextView
		)

		editsAndWarns.forEach { (editView, warnView) ->
			editView.setOnFocusChangeListener { v, hasFocus ->
				if (!hasFocus && v is EditText) {
					val value = v.text.toString().trim()

					warnView.text = when (v) {
						nameEditView -> if (value.isEmpty()) getString(R.string.signup_error_name) else ""
						emailEditView -> if (value.isEmpty()) getString(R.string.signup_error_email) else ""
						emailBodyEditView -> if (value.isEmpty()) getString(R.string.signup_error_emailBody) else ""
						passwordEditView -> if (!isPasswordValid(value)) getString(R.string.signup_error_password) else ""
						else -> ""
					}

					when (v) {
						nameEditView -> isNameValidation = value.isNotEmpty()
						emailEditView -> isEmailValidation = value.isNotEmpty()
						emailBodyEditView -> isEmailBodyValidation = value.isNotEmpty()
						passwordEditView -> isPasswordValidation = isPasswordValid(value)
						else -> {}
					}

					checkAllValidation()
				}
			}
		}

		val emailList = listOf(
			EmailData(1, "google.com"),
			EmailData(2, "naver.com"),
			EmailData(3, "직접입력")
		)
		val spinnerAdapter = EmailAdapter(this, emailList)

		spinnerView.adapter = spinnerAdapter

		spinnerView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
				if (position == emailList.size - 1) {
					emailBodyEditView.visibility = View.VISIBLE
					emailBodyWarnTextView.visibility = View.VISIBLE
					isEmailBodyValidation = false
				} else {
					emailBodyEditView.visibility = View.GONE
					emailBodyWarnTextView.visibility = View.GONE
					isEmailBodyValidation = true
				}

				checkAllValidation()
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {

			}
		}
	}

	private fun checkAllValidation() {
		val isAllValid = isNameValidation && isEmailValidation && isEmailBodyValidation && isPasswordValidation

		if (isAllValid) {
			signupBtn.isClickable = true
			signupBtn.setBackgroundColor(Color.parseColor("#FF5733"))
		} else {
			signupBtn.isClickable = false
			signupBtn.setBackgroundColor(Color.parseColor("#808080"))
		}
	}

	private fun isPasswordValid(password: String): Boolean {
		val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*(),.?\":{}|<>])")
		return regex.containsMatchIn(password)
	}
}