package com.victorduarte.themoviedbclient.loader;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

import com.victor.githubclient.loader.BaseLoader;
import com.victor.githubclient.loader.Callback;
import com.victor.githubclient.loader.Response;

public class TheMoviesDbLoaderManager {

    public static <D> void init(final LoaderManager manager,
                                final int loaderId,
                                final BaseLoader<D> loader,
                                final Callback<D> callback) {
        manager.initLoader(loaderId, Bundle.EMPTY, new LoaderCallbacks<Response<D>>() {
            @Override
            public Loader<Response<D>> onCreateLoader(int id, Bundle args) {
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<Response<D>> loader, Response<D> data) {
                if (data.hasError()) {
                    callback.onFailure(data.getException());
                } else {
                    callback.onSuccess(data.getResult());
                }

                manager.destroyLoader(loaderId);
            }

            @Override
            public void onLoaderReset(Loader<Response<D>> loader) {
                //Nothing to do here
            }
        });
    }
}
