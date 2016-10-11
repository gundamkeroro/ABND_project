package com.example.fengxinlin.nanodegreep4;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.fengxinlin.nanodegreep4.Fragment.artists;
import com.example.fengxinlin.nanodegreep4.Fragment.genres;
import com.example.fengxinlin.nanodegreep4.Fragment.songs;

public class MainActivity extends AppCompatActivity {


    private SectionsPagerAdapter SectionsPagerAdapter;

    private ViewPager ViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager = (ViewPager) findViewById(R.id.pager);
        ViewPager.setAdapter(SectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(ViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 4;
        private String tabTitles[] = new String[] { " Now Playing", "Songs", "Artists" , "Genres" };
        Context context;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {

                case 0:
                    nowPlaying tab1 = new nowPlaying();
                    return tab1;

                case 1:
                    songs tab2 = new songs();
                    return tab2;

                case 2:
                    artists tab3 = new artists();
                    return tab3;

                case 3:
                    genres tab4 = new genres();
                    return tab4;

            }
            return null;
        }

        @Override
        public int getCount() {
            // Show total pages.
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
