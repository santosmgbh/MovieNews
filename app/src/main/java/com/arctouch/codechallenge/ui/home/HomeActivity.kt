package com.arctouch.codechallenge.ui.home

import android.os.Bundle
import android.view.View.GONE
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.ui.base.BaseActivity
import kotlinx.android.synthetic.main.home_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    private val homeActivityViewModel: HomeActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        homeActivityViewModel.showUpcomingVideos().observe(this, Observer { movies ->
            recyclerView.adapter = HomeAdapter(movies)
            homeActivityViewModel.progressVisible.set(GONE)
        })
    }
}
