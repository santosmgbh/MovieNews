package com.arctouch.codechallenge.ui.detail

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.DetailActivityBinding
import com.arctouch.codechallenge.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {

    private val detailActivityViewModel: DetailActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: DetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.detail_activity)

        binding.lifecycleOwner = this
        binding.viewmodel = detailActivityViewModel


    }
}