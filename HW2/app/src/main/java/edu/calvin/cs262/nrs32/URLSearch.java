package edu.calvin.cs262.nrs32;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.annotation.Nullable;

/**
 * URLSearch is an AsyncTaskLoading that takes a url string and loads in the source code from that address in the background
 */
public class URLSearch extends AsyncTaskLoader {
    private final String urlString;

    /**
     * Save queryString as urlString for use in activity
     * @param context function context
     * @param queryString The string from user to search with
     */
    public URLSearch(@NonNull Context context, String queryString) {
        super(context);
        urlString = queryString;
    }

    /**
     * Use url string to load in source code and convert to string to return
     * @return the html resutls
     */
    @Nullable
    @Override
    public String loadInBackground() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String html = "";

        try {
            Log.d("|||||||||||||||||||||||", urlString);

            URL url = new URL(urlString);
            HttpURLConnection response = (HttpURLConnection) url.openConnection();

            // Get the InputStream.
            InputStream in = response.getInputStream();

            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(in));

            // Use a StringBuilder to hold the incoming response.
            StringBuilder str = new StringBuilder();
            String line;// = null;
            while((line = reader.readLine()) != null){
                str.append(line);
            }
            in.close();
            html = str.toString();

            if (html.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "url was not found :(";
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d("|||||||||||||||||||||||", html);
        return html;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

}