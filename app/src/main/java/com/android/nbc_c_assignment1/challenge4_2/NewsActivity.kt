package com.android.nbc_c_assignment1.challenge4_2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.nbc_login.R
import com.android.nbc_login.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {
    private val binding by lazy { ActivityNewsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)



    }
}