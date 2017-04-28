package com.victorduarte.themoviedbclient.ui.presenter

import android.content.Context
import android.support.v4.app.LoaderManager
import com.victor.githubclient.loader.BaseLoader
import com.victor.githubclient.loader.Callback
import com.victorduarte.themoviedbclient.data.Service
import com.victorduarte.themoviedbclient.data.loader.TheMoviesDbLoaderManager
import com.victorduarte.themoviedbclient.data.model.MovieResult
import com.victorduarte.themoviedbclient.ui.view.MoviesView

/**
 * Created by victor on 28/04/17.
 */
class MoviesPresenter(var context: Context, var service: Service, var view: MoviesView) {
    private var currentPage = 0
    private var currentFilter: String? = null

    fun getMovies(loaderManager: LoaderManager) {
        getMovies(loaderManager, currentFilter)
    }

    fun getMovies(loaderManager: LoaderManager, filter: String?) {
        if (view == null || context == null)
            throw Exception("attach view to use the presenter")

        if (currentFilter != filter) {
            currentPage = 0
            view.cleanData()
            view.showProgress()
        }

        currentFilter = filter

        val loader = MoviesPresenterLoader(context, service, currentFilter, currentPage + 1)
        val loaderId = if (currentFilter != null) currentFilter!!.hashCode() else 0 + currentPage + 1
        TheMoviesDbLoaderManager.init(loaderManager, loaderId, loader, (object : Callback<MovieResult?> {
            override fun onFailure(ex: Exception) {
                view?.showError()
                view?.hideProgress()
            }

            override fun onSuccess(result: MovieResult?) {
                if (result != null) {
                    currentPage = result.page

                    if (result.totalPages == result.page)
                        view?.onLastPage()

                    view?.showItems(result.results)
                    view?.hideError()
                    view?.hideProgress()
                } else {
                    view?.showError()
                    view?.hideProgress()
                }
            }
        }))
    }

    class MoviesPresenterLoader(context: Context,
                                private val service: Service,
                                private val filter: String?,
                                private val page: Int) : BaseLoader<MovieResult>(context) {
        override fun call(): MovieResult {
            if (filter != null && !filter.isEmpty())
                return service.searchMovies(filter, page)
            else
                return service.getMovies(page = page)
        }
    }
}