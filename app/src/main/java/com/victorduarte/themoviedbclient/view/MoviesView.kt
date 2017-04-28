package com.victorduarte.themoviedbclient.view

import com.victorduarte.themoviedbclient.model.Result

/**
 * Created by victor on 28/04/17.
 */
interface MoviesView {
    fun showProgress()

    fun hideProgress()

    fun showItems(items: MutableList<Result>)

    fun showError()

    fun hideError()

    fun cleanData()
}