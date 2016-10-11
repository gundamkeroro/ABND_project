package com.example.fengxinlin.nanodegreep7;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    public TextView emptyView;
    private static String urlString;

    private static final String TAG_TITLE = "title";
    private static final String TAG_AUTHORS = "authors";

    ArrayList<HashMap<String, String>> booklist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);
        emptyView = (TextView) findViewById(R.id.empty);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.input);
                String input = editText.getText().toString();
                input = input.replace(" ", "+");
                urlString = "https://www.googleapis.com/books/v1/volumes?q="+input+"&orderBy=newest";
                if (isConnected()) {
                    Toast.makeText(MainActivity.this, R.string.good, Toast.LENGTH_SHORT).show();
                    new ProcessJSON().execute(urlString);
                } else {
                    Toast.makeText(MainActivity.this, R.string.bad, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager)MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    //AsyncTask below
    private class ProcessJSON extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, R.string.loading, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];
            HTTPHandler hh = new HTTPHandler();
            stream = hh.GetHTTPData(urlString);
            return stream;
        }

        @Override
        protected void onPostExecute(String stream) {
            listView = (ListView) findViewById(R.id.list);
            if (stream != null) {
                try {
                    JSONObject jsonObject = new JSONObject(stream);
                    int totalItems = jsonObject.getInt("totalItems");
                    if (totalItems == 0) {
                        listView.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, R.string.no_result, Toast.LENGTH_SHORT).show();
                    } else {
                        emptyView.setText("");
                        JSONArray books = jsonObject.getJSONArray("items");

                        for (int i = 0; i < books.length(); i++) {
                            JSONObject bookObject = books.getJSONObject(i);

                            String title;
                            String author;

                            JSONObject bookDetails = bookObject.getJSONObject("volumeInfo");

                            if (bookDetails.has("authors")) {
                                author = bookDetails.getString("authors");
                                author = author.replace("[", "");
                                author = author.replace("]", "");
                            } else {
                                author = "";
                            }

                            title = bookDetails.getString("title");
                            HashMap<String, String> mp = new HashMap<>();
                            mp.put(TAG_TITLE, title);
                            mp.put(TAG_AUTHORS, author);
                            booklist.add(mp);
                        }
                        ListAdapter adapter = new SimpleAdapter(MainActivity.this,
                                booklist,
                                R.layout.book_listview_item,
                                new String[] {TAG_TITLE, TAG_AUTHORS},
                                new int[] {R.id.bookTitle, R.id.bookAuthors});
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Toast.makeText(MainActivity.this, "Description:\n  " + booklist.get(+i), Toast.LENGTH_SHORT).show();
                            }

                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
