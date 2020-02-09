package com.arctouch.codechallenge.ui.detail

import android.os.Bundle
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {

    private val detailActivityViewModel: DetailActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
    }
}