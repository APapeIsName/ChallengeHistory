package com.android.nbc_c_assignment1.challenge3.nbc_login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.android.nbc_login.R

class SignUpActivity : AppCompatActivity() {
    // 뷰모델 설정
    private val viewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSignUp = findViewById<Button>(R.id.btn_signup)
        val etName = findViewById<EditText>(R.id.et_name)
        val etId = findViewById<EditText>(R.id.et_signUpId)
        val etPwd = findViewById<EditText>(R.id.et_signUpPwd)
        val etChicken = findViewById<EditText>(R.id.et_singUpChicken)

        // 라이브 데이터 감시
        viewModel.user.observe(this) {
            Toast.makeText(this, viewModel.getUser().toString(), Toast.LENGTH_SHORT).show()
        }

        btnSignUp.setOnClickListener {
            if (
                etName.text.toString().isEmpty() ||
                etId.text.toString().isEmpty() ||
                etPwd.text.toString().isEmpty() ||
                etChicken.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
//                val user = UserClass(
//                    etId.text.toString(),
//                    etPwd.text.toString(),
//                    etName.text.toString(),
//                    etChicken.text.toString()
//                )
                viewModel.changeUser(etId.text.toString(), etPwd.text.toString(), etName.text.toString(), etChicken.text.toString())
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("user", viewModel.getUser())
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}