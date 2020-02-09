package com.arctouch.codechallenge.ui.home

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.HomeActivityBinding
import com.arctouch.codechallenge.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    private val homeActivityViewModel: HomeActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: HomeActivityBinding = DataBindingUtil.setContentView(this, R.layout.home_activity)

        binding.lifecycleOwner = this
        binding.viewmodel = homeActivityViewModel

        val homeAdapter = HomeAdapter()
        binding.recyclerView.adapter = homeAdapter
        homeActivityViewModel.moviesLiveData.observe(this, Observer {
            homeAdapter.submitList(it)
        })

        homeActivityViewModel.progressLoadStatus.observe(this, Observer {
            if (it == HomeDataSource.LoadingStatus.LOADING) {
                homeActivityViewModel.progressVisible.set(VISIBLE)
            } else if (it == HomeDataSource.LoadingStatus.LOADED) {
                homeActivityViewModel.progressVisible.set(GONE)
            }
        })
    }
}
