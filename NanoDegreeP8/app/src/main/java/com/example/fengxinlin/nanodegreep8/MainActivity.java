package com.example.fengxinlin.nanodegreep8;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<String> {

    private static final String API = "&api-key=981a0d3b-96f0-4267-9476-1ef62a049ffb";
    private static final String pageSize = "&page-size=20";
    private static final String contributor = "&show-tags=contributor";
    public static final String urlString = "https://content.guardianapis.com/search?" + API + pageSize + contributor;
    public String urlFinal;
    private static final String TAG_ID = "id";
    private static final String TAG_SECTION_NAME = "sectionName";
    private static final String TAG_TITLE = "webTitle";
    private static final String TAG_WEB_URL = "webUrl";
    private static final String TAG_DATE = "webPublicationDate";

    private static final String TAG = "tags";
    private static final int AUTHOR = 0;

    private static final int NEWS_LOADER_ID = 1;

    @BindView(R.id.list) ListView list;
    @BindView(R.id.empty) TextView emptyView;
    @BindView(R.id.btn) Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        emptyView.setText("");


        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.input);
                String inputQuery = editText.getText().toString();
                inputQuery = inputQuery.replace(" ","+");

                urlFinal = urlString + "&q="+inputQuery;
                
                //new ProcessJSON().execute(urlFinal);
            }
        });
        //new ProcessJSON().execute(urlString);
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, urlString);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String stream) {
        final ArrayList<HashMap<String, String>> newsList = new ArrayList<>();
        if (stream != null) {
            try {
                JSONObject reader = new JSONObject(stream);
                JSONObject newsArray = reader.getJSONObject("response");
                int total = newsArray.getInt("total");
                if (total == 0) {
                    list.setVisibility(View.INVISIBLE);
                    emptyView.setText(R.string.empty);
                    Toast.makeText(MainActivity.this, "No Results Found", Toast.LENGTH_SHORT).show();
                } else {
                    emptyView.setText("");
                    JSONArray newsResults = newsArray.getJSONArray("results");
                    for (int i = 0; i < 20; i++) {

                        JSONObject jsonObject = newsResults.getJSONObject(i);
                        String title, URL, section, date, ID;
                        String author;

                        title = jsonObject.getString(TAG_TITLE);
                        URL = jsonObject.getString(TAG_WEB_URL);
                        section = jsonObject.getString(TAG_SECTION_NAME);
                        date = jsonObject.getString(TAG_DATE);
                        ID = String.valueOf(i + 1);
                        ID = ID + ".";

                        JSONArray tagjsonArray = jsonObject.getJSONArray(TAG);
                        JSONObject authorObject = tagjsonArray.getJSONObject(AUTHOR);
                        author = authorObject.getString(TAG_TITLE);

                        HashMap<String, String> map = new HashMap<>();
                        map.put(TAG_ID, ID);
                        map.put(TAG_TITLE, title);
                        map.put(TAG_SECTION_NAME, section);
                        map.put(TAG_WEB_URL, URL);
                        map.put(TAG_DATE, date);
                        map.put(TAG_TITLE, title);

                        map.put(TAG, author);
                        newsList.add(map);
                        ListAdapter adapter = new SimpleAdapter(MainActivity.this,
                                newsList,
                                R.layout.listview_news,
                                new String[]{TAG_TITLE, TAG_DATE, TAG_SECTION_NAME, TAG, TAG_WEB_URL},
                                new int[]{R.id.news_title, R.id.date, R.id.section, R.id.author}
                        );

                        list.setAdapter(adapter);
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                String url = newsList.get(+position).get(TAG_WEB_URL);
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);
                            }
                        });

                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}