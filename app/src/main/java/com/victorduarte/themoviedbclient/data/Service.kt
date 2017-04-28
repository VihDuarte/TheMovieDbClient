package com.victorduarte.themoviedbclient.data

import com.victorduarte.themoviedbclient.data.model.MovieResult

/**
 * Created by victor on 28/04/17.
 */
interface Service {
    fun getMovies(page: Int): MovieResult
    fun searchMovies(search: String, page: Int): MovieResult
}