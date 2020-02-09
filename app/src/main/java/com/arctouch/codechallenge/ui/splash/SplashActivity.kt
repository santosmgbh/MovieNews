package com.arctouch.codechallenge.ui.splash

import android.content.Intent
import android.os.Bundle
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.ui.base.BaseActivity
import com.arctouch.codechallenge.ui.home.HomeActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {


    val splashActivityViewModel: SplashActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        splashActivityViewModel.cacheGenres {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }


    }
}
