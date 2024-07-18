package com.android.nbc_c_assignment1.challenge4_2

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.android.nbc_login.R
import com.android.nbc_login.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity(), DetailDataListener {
    private val binding by lazy { ActivityNewsBinding.inflate(layoutInflater) }
    val titleFragment = NewsTitleFragment.newInstance("")
    var detailFragment = NewsDetailFragment.newInstance("비어있음")
    val config = Resources.getSystem().configuration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        println(resources.configuration.orientation)

        setFragment(config)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setFragment(newConfig)
    }

    fun setFragment(config: Configuration, isClick: Boolean = false) {
        if( config.orientation == Configuration.ORIENTATION_PORTRAIT ) {
            if(!isClick) {
                supportFragmentManager.commit {
                    replace(R.id.fragment, titleFragment)
                    setReorderingAllowed(true)
                    addToBackStack("title")
                }
            } else {
                supportFragmentManager.commit {
                    replace(R.id.fragment, detailFragment)
                    setReorderingAllowed(true)
                    addToBackStack("detail")
                }
            }

        } else if ( config.orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_title, titleFragment)
//                addToBackStack("title")
                replace(R.id.fragment_detail, detailFragment)
//                addToBackStack("detail")
            }
        }
    }

    override fun onReceive(detail: String) {
        detailFragment = NewsDetailFragment.newInstance(detail)
        setFragment(config, true)
    }
}