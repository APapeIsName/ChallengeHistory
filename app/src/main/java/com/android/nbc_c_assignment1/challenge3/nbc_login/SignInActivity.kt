package com.android.nbc_c_assignment1.challenge3.nbc_login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.nbc_login.R

class SignInActivity : AppCompatActivity() {
    private lateinit var user : UserClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id = findViewById<EditText>(R.id.et_id)
        val pwd = findViewById<EditText>(R.id.et_pwd)
        val startForRes =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    user = result.data?.getParcelableExtra<UserClass>("user")!!
                    id.setText(user.id)
                    pwd.setText(user.pwd)
                }
            }
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            if (id.text.toString().isBlank() || pwd.text.toString().isBlank()) {
                Toast.makeText(this, "ID/비밀번호를 전부 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(!::user.isInitialized)
                Toast.makeText(this, "회원가입을 먼저 해주세요.", Toast.LENGTH_SHORT).show()
            else if(user.id != id.text.toString() || user.pwd != pwd.text.toString())
                Toast.makeText(this, "가입되지 않은 ID이거나, 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
        }
        val btnSignUp = findViewById<Button>(R.id.btn_register)
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startForRes.launch(intent)
        }
    }
}