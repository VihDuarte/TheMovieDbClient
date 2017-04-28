package com.victorduarte.themoviedbclient.data.model

import org.json.JSONObject

/**
 * Created by victor on 28/04/17.
 */
data class Movie(var id: Int,
                 var posterPath: String,
                 var overview: String,
                 var releaseDate: String,
                 var title: String,
                 var backdropPath: String,
                 var popularity: Double,
                 var voteAverage: Double)

fun getResulByJson(json: JSONObject): Movie {
    var id: Int = 0
    var posterPath = ""
    var overview = ""
    var releaseDate = ""
    var title = ""
    var backdropPath = ""
    var popularity = 0.0
    var voteAverage = 0.0

    if (json.has("id"))
        id = json.getInt("id")

    if (json.has("poster_path"))
        posterPath = json.getString("poster_path")

    if (json.has("overview"))
        overview = json.getString("overview")

    if (json.has("release_date"))
        releaseDate = json.getString("release_date")

    if (json.has("title"))
        title = json.getString("title")

    if (json.has("backdrop_path"))
        backdropPath = json.getString("backdrop_path")

    if (json.has("popularity"))
        popularity = json.getDouble("popularity")

    if (json.has("vote_average"))
        voteAverage = json.getDouble("vote_average")


    return Movie(id,
            posterPath,
            overview,
            releaseDate,
            title,
            backdropPath,
            popularity,
            voteAverage)
}