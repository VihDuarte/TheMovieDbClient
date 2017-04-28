package com.victorduarte.themoviedbclient.model

import org.json.JSONObject

/**
 * Created by victor on 28/04/17.
 */
data class MovieResult(var page: Int,
                       var results: MutableList<Result>,
                       var totalResults: Int,
                       var totalPages: Int)

fun getMovieResulByJson(json: JSONObject): MovieResult {
    var page = 0
    val results: MutableList<Result> = arrayListOf()
    var totalResults = 0
    var totalPages = 0

    if (json.has("page"))
        page = json.getInt("page")

    if (json.has("results")) {
        var items = json.getJSONArray("results")
        (0..(items.length() - 1)).mapTo(results) {
            getResulByJson(items.getJSONObject(it))
        }
    }

    if (json.has("total_results"))
        totalResults = json.getInt("total_results")

    if (json.has("total_pages"))
        totalPages = json.getInt("total_pages")

    return MovieResult(page,
            results,
            totalResults,
            totalPages)
}
