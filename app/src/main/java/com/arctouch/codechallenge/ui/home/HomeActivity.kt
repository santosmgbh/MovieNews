package com.arctouch.codechallenge.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.HomeActivityBinding
import com.arctouch.codechallenge.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeActivityViewModel: HomeActivityViewModel by viewModel()
    private val homeAdapter by lazy {
        HomeAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: HomeActivityBinding = DataBindingUtil.setContentView(this, R.layout.home_activity)

        binding.lifecycleOwner = this
        binding.viewmodel = homeActivityViewModel

        homeAdapter.onItemClick = {
            onClickItemList(binding, it, homeAdapter)

        }
        binding.recyclerView.adapter = homeAdapter

        configObservers()
        configActionBar()
    }

    private fun configObservers() {
        homeActivityViewModel.moviesLiveData.observe(this, Observer {
            homeAdapter.submitList(it)
        })
        homeActivityViewModel.progressLoadStatus.observe(this, Observer {
            homeActivityViewModel.updateProgressStatus(it)
        })
    }

    private fun configActionBar() {
        supportActionBar?.let {
            it.title = getString(R.string.home_toolbar_title)
        }
    }

    private fun onClickItemList(binding: HomeActivityBinding, it: View, homeAdapter: HomeAdapter) {
        val movieId = homeActivityViewModel.getMovieId(it, binding.recyclerView, homeAdapter)
        movieId?.let {
            startActivity(DetailActivity.openActivityIntent(this, it))
        }
    }
}
