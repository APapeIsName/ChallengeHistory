package com.android.nbc_c_assignment1.challenge3.nbc_login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.nbc_login.R
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    val imageResources = intArrayOf(
        R.drawable.kyochon_1,
        R.drawable.kyochon_2,
        R.drawable.kyochon_3,
        R.drawable.kyochon_4,
        R.drawable.kyochon_5,
    )
    val urlResources = arrayOf(
        "https://m.kyochon.com/menu/menu_view?product_id=30098&depth1=2",
        "https://m.kyochon.com/menu/menu_view?product_id=30161&depth1=2",
        "https://m.kyochon.com/menu/menu_view?product_id=30022&depth1=2",
        "https://m.kyochon.com/menu/menu_view?product_id=39242&depth1=7",
        "https://m.kyochon.com/menu/menu_view?product_id=39270&depth1=2"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val random = Random.nextInt(5)
        val user = intent.getParcelableExtra<UserClass>("user")
        val tv = findViewById<TextView>(R.id.tv_info)
        val btn = findViewById<ConstraintLayout>(R.id.cl_btn_chicken)
        val tvBtn = findViewById<TextView>(R.id.tv_btn_text)
        val switch = findViewById<SwitchCompat>(R.id.sw_btnChange)
        val ivBtn = findViewById<ImageView>(R.id.iv_btn_image)
        tv.text = "아이디 : ${user?.id} \n\n" +
                "이름 : ${user?.name ?: "공명선"}\n\n" +
                "좋아하는 치킨: ${user?.chicken ?: "후라이드"}"
        val iv = findViewById<ImageView>(R.id.iv_chicken)
        iv.clipToOutline = true
        Log.e("randomValue", "$random")
        iv.setImageResource(imageResources[random])
        btn.setOnClickListener {
            finish()
        }
        switch.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                tvBtn.text = "치킨 사먹으러 바로 고고"
                ivBtn.setImageResource(R.drawable.btn_home_chicken_emoji)
                button.text = "종료로 바꾸기"
                btn.setOnClickListener {
                    val intentUri = Intent(Intent.ACTION_VIEW, Uri.parse(urlResources[random]))
                    startActivity(intentUri)
                }
            }
            else {
                tvBtn.text = "종료"
                ivBtn.setImageResource(R.drawable.btn_home_emoji)
                button.text = "링크로 바꾸기"
                btn.setOnClickListener {
                    finish()
                }
            }
        }
    }
}