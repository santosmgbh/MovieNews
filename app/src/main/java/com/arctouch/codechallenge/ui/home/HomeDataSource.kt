package com.arctouch.codechallenge.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repository.MovieRepository
import org.koin.core.KoinComponent


class HomeDataSource(val repository: MovieRepository) : PageKeyedDataSource<Int, Movie>(), KoinComponent {


    val progressLiveStatus: MutableLiveData<LoadingStatus> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        progressLiveStatus.postValue(LoadingStatus.LOADING)
        repository.getUpcomingMovies(1) {
            var movies = it.results
            callback.onResult(movies, 1, it.totalResults, null, 2)
            progressLiveStatus.postValue(LoadingStatus.LOADED)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        progressLiveStatus.postValue(LoadingStatus.LOADING)
        repository.getUpcomingMovies(params.key.toLong()) {
            var movies = it.results
            callback.onResult(movies, params.key + 1)
            progressLiveStatus.postValue(LoadingStatus.LOADED)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        progressLiveStatus.postValue(LoadingStatus.LOADING)
        repository.getUpcomingMovies(params.key.toLong()) {
            var movies = it.results
            callback.onResult(movies, params.key - 1)
            progressLiveStatus.postValue(LoadingStatus.LOADED)
        }
    }

    enum class LoadingStatus {
        LOADING, LOADED
    }
}