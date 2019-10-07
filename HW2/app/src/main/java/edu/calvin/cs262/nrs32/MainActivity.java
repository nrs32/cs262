package edu.calvin.cs262.nrs32;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    Spinner protocol;
    String[] dropdownOptions = {"https://", "http://"};
    private EditText websiteURL;
    private TextView pageSourceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create protocol options dropdown
        protocol = (Spinner) findViewById(R.id.protocol_dropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dropdownOptions);
        protocol.setAdapter(adapter);

        websiteURL = (EditText)findViewById(R.id.websiteURLInput);
        pageSourceText = (TextView)findViewById(R.id.pageSourceText);
    }

    // Create connection and URL string combining protocol and source address
    public void getPageSource(View view) {
        // Get the search string from the input field.
        String urlString = websiteURL.getText().toString();
        String protocolType = protocol.getSelectedItem().toString();

        // Being to setup connection
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null ) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        // If user has given input and we are connected to network
        if (networkInfo != null && networkInfo.isConnected()
                && urlString.length() != 0 && protocolType.length() != 0) {

            // Combine protocol string with user typed url to create final url
            String finalSearchString = protocolType.concat(urlString);
            Bundle queryBundle = new Bundle();
            queryBundle.putString("urlString", finalSearchString);

            // Restart loader to get results
            getSupportLoaderManager().restartLoader(0, queryBundle, this);

            pageSourceText.setText("Loading...");

        // No search term or no network
        } else {
            if (urlString.length() == 0) {
                pageSourceText.setText("");
            } else {
                pageSourceText.setText("");
            }
        }
    }

    // Call URLSearch to retrieve source code from the given URL
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String urlSource = "";

        if (args != null) { urlSource = args.getString("urlString"); }

        return new URLSearch(this, urlSource);
    }

    // Get the results from URLSearch and display them
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        String results = data;
        if (results != null) {
            pageSourceText.setText(results);

        // no valid response
        } else {
            pageSourceText.setText("");
        }
    }

    // Required stub, intentionally unused
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }
}
