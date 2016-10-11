package com.example.fengxinlin.nanodegreep8;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by fengxinlin on 10/6/16.
 */
public class NewsLoader extends AsyncTaskLoader<String>{
    private String url;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        String stream = null;
        String urlString = url;
        HTTPHandler httpHandler = new HTTPHandler();
        stream = httpHandler.getHTTPData(urlString);
        return stream;
    }
}
