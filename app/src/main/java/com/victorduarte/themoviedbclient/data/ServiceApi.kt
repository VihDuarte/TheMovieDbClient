package com.victorduarte.themoviedbclient.data

import com.victorduarte.themoviedbclient.BuildConfig
import com.victorduarte.themoviedbclient.data.model.MovieResult
import com.victorduarte.themoviedbclient.data.model.getMovieResulByJson
import org.json.JSONObject
import java.net.URL

/**
 * Created by victor on 28/04/17.
 */

class ServiceApi : Service {
    private val token = "b414171ac4360b4973b0255f259cb5e7"

    override fun getMovies(page: Int): MovieResult {
        val url = BuildConfig.BASE_URL + "discover/movie?sort_by=popularity.desc&page=$page&api_key=$token"
        val json = URL(url).readText()
        return getMovieResulByJson(JSONObject(json))
    }

    override fun searchMovies(search: String, page: Int): MovieResult {
        val url = BuildConfig.BASE_URL + "search/movie?query=$search&page=$page&api_key=$token"
        val json = URL(url).readText()
        return getMovieResulByJson(JSONObject(json))
    }
}