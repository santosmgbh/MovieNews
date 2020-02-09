package com.arctouch.codechallenge.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.DetailActivityBinding
import com.arctouch.codechallenge.ui.base.BaseActivity
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_activity.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : BaseActivity() {

    private val detailActivityViewModel: DetailActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: DetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.detail_activity)

        binding.lifecycleOwner = this
        binding.viewmodel = detailActivityViewModel

        val movieId = intent.getLongExtra(MOVIE_ID, 0)


        detailActivityViewModel.loadMovie(movieId).observe(this, Observer {
            detailActivityViewModel.name.set(it.title)
            detailActivityViewModel.overview.set(it.overview)
            detailActivityViewModel.releaseDate.set(it.releaseDate)
            it.posterPath?.let { url ->
                Glide.with(this).load(MovieImageUrlBuilder().buildPosterUrl(url)).into(ivPoster)
            }
            it.backdropPath?.let { url ->
                Glide.with(this).load(MovieImageUrlBuilder().buildBackdropUrl(url)).into(ivBackdrop)
            }
        })

    }


    companion object {
        private const val MOVIE_ID: String = "movie_id"

        fun openActivityIntent(context: Context, id: Long): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_ID, id)
            return intent
        }
    }
}