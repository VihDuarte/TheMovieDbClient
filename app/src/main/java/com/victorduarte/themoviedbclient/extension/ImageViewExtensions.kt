package com.victor.githubclient.extensions

import android.widget.ImageView
import com.victorduarte.themoviedbclient.BuildConfig
import com.victorduarte.themoviedbclient.data.DownloadImageTask
import com.victorduarte.themoviedbclient.data.ImageLoader

fun ImageView.loadImage(idImage: String?) {
    val url = BuildConfig.BASE_IMAGE_URL + idImage

    if (!ImageLoader.downloads.isEmpty()) {
        ImageLoader.downloads.filter { it.imageView.equals(this) }.forEach {
            it.cancel(true)
            ImageLoader.downloads.remove(it)
        }
    }

    if (ImageLoader.cache[url] != null) {
        this.setImageBitmap(ImageLoader.cache[url])
    } else {
        this.setImageResource(0)

        val item = DownloadImageTask(this)
        item.execute(url)

        ImageLoader.downloads.add(item)
    }
}

