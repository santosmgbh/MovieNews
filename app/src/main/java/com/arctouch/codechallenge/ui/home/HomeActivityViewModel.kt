package com.arctouch.codechallenge.ui.home

import android.view.View
import android.view.View.VISIBLE
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repository.MovieRepository

class HomeActivityViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun updateProgressStatus(it: HomeDataSource.LoadingStatus) {
        if (it == HomeDataSource.LoadingStatus.LOADING) {
            progressVisible.set(VISIBLE)
        } else if (it == HomeDataSource.LoadingStatus.LOADED) {
            progressVisible.set(View.GONE)
        }
    }

    fun getMovieId(it: View, recyclerView: RecyclerView, adapter: HomeAdapter): Long? {
        val child = recyclerView.getChildLayoutPosition(it)

        val movie = adapter.getItemFromPosition(child)
        return movie?.id
    }

    var moviesLiveData: LiveData<PagedList<Movie>>
    var progressLoadStatus: LiveData<HomeDataSource.LoadingStatus>

    init {
        val moviesDataSourceFactory = HomeDataSourceFactory()
        val pagedListConfig = PagedList.Config.Builder().setInitialLoadSizeHint(20).setPageSize(20).build()
        moviesLiveData = LivePagedListBuilder(moviesDataSourceFactory, pagedListConfig).build()
        progressLoadStatus = Transformations.switchMap(moviesDataSourceFactory.liveData, HomeDataSource::progressLiveStatus);
    }

    val progressVisible: ObservableInt = ObservableInt(VISIBLE)


}