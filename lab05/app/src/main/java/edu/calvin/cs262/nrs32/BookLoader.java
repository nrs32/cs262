package edu.calvin.cs262.nrs32;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.annotation.Nullable;

public class BookLoader extends AsyncTaskLoader {
    private String mQueryString;

    public BookLoader(@NonNull Context context, String queryString) {
        super(context);
        mQueryString = queryString;

    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }

}