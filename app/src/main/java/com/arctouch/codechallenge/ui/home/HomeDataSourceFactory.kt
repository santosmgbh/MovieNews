package com.arctouch.codechallenge.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repository.MovieRepository
import org.koin.core.KoinComponent
import org.koin.core.inject


class HomeDataSourceFactory : DataSource.Factory<Int, Movie>(), KoinComponent {

    private val repository: MovieRepository by inject()

    val liveData: MutableLiveData<HomeDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Movie> {
        val moviesDataSource = HomeDataSource(repository)
        liveData.postValue(moviesDataSource)
        return moviesDataSource

    }
}