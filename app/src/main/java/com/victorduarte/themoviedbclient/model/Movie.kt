package com.victorduarte.themoviedbclient.model

import org.json.JSONObject

/**
 * Created by victor on 28/04/17.
 */
data class Result(var id: Int,
                  var posterPath: String,
                  var overview: String,
                  var releaseDate: String,
                  var title: String,
                  var backdropPath: String,
                  var popularity: Double,
                  var voteCount: Int,
                  var voteAverage: Double)

fun getResulByJson(json: JSONObject): Result {
    var id: Int = 0
    var posterPath = ""
    var overview = ""
    var releaseDate = ""
    var title = ""
    var backdropPath = ""
    var popularity = 0.0
    var voteCount = 0
    var voteAverage = 0.0

    if (json.has("page"))
        id = json.getInt("page")

    if (json.has("page"))
        posterPath = json.getString("page")

    if (json.has("page"))
        overview = json.getString("page")

    if (json.has("page"))
        releaseDate = json.getString("page")

    if (json.has("page"))
        title = json.getString("page")

    if (json.has("page"))
        backdropPath = json.getString("page")

    if (json.has("page"))
        popularity = json.getDouble("page")

    if (json.has("page"))
        voteCount = json.getInt("page")

    if (json.has("page"))
        voteAverage = json.getDouble("page")


    return Result(id,
            posterPath,
            overview,
            releaseDate,
            title,
            backdropPath,
            popularity,
            voteCount,
            voteAverage)
}