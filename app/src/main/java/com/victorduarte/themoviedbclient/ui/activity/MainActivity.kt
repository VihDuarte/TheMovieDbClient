package com.victorduarte.themoviedbclient.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View

import com.victorduarte.themoviedbclient.R
import com.victorduarte.themoviedbclient.data.model.Movie
import com.victorduarte.themoviedbclient.ui.adapter.MoviesListAdapter
import com.victorduarte.themoviedbclient.ui.presenter.MoviesPresenter
import com.victorduarte.themoviedbclient.ui.view.MoviesView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MoviesView, SearchView.OnQueryTextListener {
    private var itemList: MutableList<Movie>? = null

    private var movieListAdapter: MoviesListAdapter? = null

    private var presenter: MoviesPresenter? = null
    private var snackbar: Snackbar? = null
    private var inLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(activity_main_toolbar)

        presenter = MoviesPresenter(this, this)

        initRecyclerView()

        if (itemList == null || itemList!!.size == 0) {
            presenter?.getMovies(supportLoaderManager)
        } else {
            movieListAdapter = MoviesListAdapter(itemList!!)
            activity_main_recyclerview?.adapter = movieListAdapter
        }
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)

        activity_main_recyclerview?.setHasFixedSize(true)
        activity_main_recyclerview?.layoutManager = layoutManager

        activity_main_recyclerview?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (layoutManager
                        .findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1 && !inLastPage) {
                    presenter?.getMovies(supportLoaderManager)
                }
            }
        })
    }

    override fun showItems(items: MutableList<Movie>) {
        if (movieListAdapter == null) {
            itemList = items
            movieListAdapter = MoviesListAdapter(items)
            activity_main_recyclerview?.adapter = movieListAdapter
        } else {
            itemList?.addAll(items)
            movieListAdapter!!.notifyDataSetChanged()
        }
    }

    override fun showProgress() {
        activity_main_progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        activity_main_progress.visibility = View.GONE
    }

    override fun showError() {
        snackbar = Snackbar.make(activity_main as View,
                R.string.movie_list_error,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry,
                        { view -> presenter?.getMovies(supportLoaderManager) })

        snackbar!!.show()
    }

    override fun hideError() {
        if (snackbar != null && snackbar!!.isShown) {
            snackbar!!.dismiss()
        }
    }

    override fun cleanData() {
        itemList?.clear()
        onLastPage()
    }

    override fun onLastPage() {
        inLastPage = true
        movieListAdapter?.inLastPage = true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextChange(query: String): Boolean {
        presenter?.getMovies(supportLoaderManager, query)
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }
}
