package com.victorduarte.themoviedbclient.presenter

import android.content.Context
import android.support.v4.app.LoaderManager
import com.victor.githubclient.loader.BaseLoader
import com.victor.githubclient.loader.Callback
import com.victorduarte.themoviedbclient.data.getMovies
import com.victorduarte.themoviedbclient.loader.TheMoviesDbLoaderManager
import com.victorduarte.themoviedbclient.model.MovieResult
import com.victorduarte.themoviedbclient.view.MoviesView

/**
 * Created by victor on 28/04/17.
 */
class MoviesPresenter {
    private var context: Context? = null
    private var view: MoviesView? = null
    private var currentPage = 0

    fun getMovies(loaderManager: LoaderManager) {
        if (view == null || context == null)
            throw Exception("attach view to use the presenter")

        view?.showProgress()

        val loader = MoviesPresenterLoader(context!!, currentPage + 1)
        TheMoviesDbLoaderManager.init(loaderManager, currentPage + 1, loader, (object : Callback<MovieResult?> {
            override fun onFailure(ex: Exception) {
                view?.hideProgress()
                view?.showError()
            }

            override fun onSuccess(result: MovieResult?) {
                if (result != null) {
                    currentPage = result.page

                    view?.showItems(result.results)
                    view?.hideError()
                    view?.hideProgress()
                } else {
                    view?.hideProgress()
                    view?.showError()
                }
            }
        }))
    }

    class MoviesPresenterLoader(context: Context,
                                private val page: Int) : BaseLoader<MovieResult>(context) {
        override fun call(): MovieResult {
            return getMovies(page = page)
        }
    }
}