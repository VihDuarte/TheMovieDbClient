package com.victorduarte.themoviedbclient.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.victor.githubclient.extensions.loadImage
import com.victorduarte.themoviedbclient.R
import com.victorduarte.themoviedbclient.data.model.Movie
import kotlinx.android.synthetic.main.loader_item_layout.view.*
import kotlinx.android.synthetic.main.movie_item.view.*

/**
 * Created by victor on 28/04/17.
 */
class MoviesListAdapter(val items: List<Movie>)
    : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 1
    private val VIEW_TYPE_LOADER = 2
    var inLastPage = false

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position != 0 && position == itemCount - 1) {
            return VIEW_TYPE_LOADER
        } else {
            return VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == VIEW_TYPE_LOADER) {
            val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.loader_item_layout, parent, false)

            return LoaderViewHolder(v)
        } else if (viewType == VIEW_TYPE_ITEM) {
            val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item, parent, false)
            return ItemViewHolder(v)
        }

        throw IllegalArgumentException("Invalid ViewType: " + viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }


    abstract inner class ViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        abstract fun bindItem(movie: Movie)
    }

    inner class ItemViewHolder(itemView: View) : ViewHolder(itemView) {
        override fun bindItem(movie: Movie) {
            itemView.movie_item_title.text = movie.title
            itemView.movie_item_overview.text = movie.overview
            itemView.movie_item_average.text = movie.voteAverage.toString()

            if (!movie.posterPath.isEmpty())
                itemView.movie_item_img.loadImage(movie.posterPath)
            else if (!movie.backdropPath.isEmpty())
                itemView.movie_item_img.loadImage(movie.backdropPath)
        }

    }

    inner class LoaderViewHolder(itemView: View) : ViewHolder(itemView) {
        override fun bindItem(movie: Movie) {
            if (inLastPage)
                itemView.progress.visibility = View.GONE
            else
                itemView.progress.visibility = View.VISIBLE
        }
    }
}