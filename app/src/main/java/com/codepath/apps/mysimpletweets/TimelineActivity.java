package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import fragments.HomeTimeLineFragment;
import fragments.MentionsTimelineFragment;

public class TimelineActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //get the viewpager
        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        //Set the viewpager adapter for the pager
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        //Find the pager sliding tabstrip
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        //Attached the tabstrip to the viewpager
        tabStrip.setViewPager(vpPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void onProfileView(MenuItem mi) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    //return the order of the fragments in the view adapter
    public class TweetsPagerAdapter extends FragmentPagerAdapter {
        private String tabTitles [] = { "Home", "Mentions"};

        //adapter get the manager insert or remove fragment activity
        public TweetsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //The order and creation of fragment within the pager
        @Override
        public Fragment getItem(int position) {
            if(position == 0) {
                Log.d("DEBUG", Integer.toString(position));
                return new HomeTimeLineFragment();
            } else if (position == 1) {
                Log.d("DEBUG", Integer.toString(position));
                return new MentionsTimelineFragment();
            } else {
                Log.d("DEBUG", Integer.toString(position));
                return null;
            }
        }

        //return tab title
        @Override
        public CharSequence getPageTitle(int position) {
            //return super.getPageTitle(position);
            if(position == 0) {
                return "HOME";
            } else if (position == 1) {
                return "Mentions";
            } else {
                return null;
            }
        }

        //how many tabs
        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }

    /*public void launchComposeView () {

        client.getVerifyCredentials(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                Log.d("DEBUG", json.toString());
                try{
                    Log.d("DEBUG", json.getString("name"));
                    Intent i = new Intent(TimelineActivity.this, ComposeActivity.class);
                    i.putExtra("username", json.getString("name"));
                    i.putExtra("userscreenname", json.getString("screen_name"));
                    i.putExtra("userimageurl", json.getString("profile_image_url"));
                    i.putExtra("code", 400);
                    startActivityForResult(i, REQUEST_CODE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            tweets.clear();
            populateTimeline(0L);
        }
    }*/
}
