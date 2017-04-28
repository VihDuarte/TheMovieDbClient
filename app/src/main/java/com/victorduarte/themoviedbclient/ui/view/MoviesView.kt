package com.victorduarte.themoviedbclient.ui.view

import com.victorduarte.themoviedbclient.data.model.Movie

/**
 * Created by victor on 28/04/17.
 */
interface MoviesView {
    fun showItems(items: MutableList<Movie>)

    fun showError()

    fun hideError()

    fun cleanData()
}