package fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by lzhan on 2/11/15.
 */
public class MentionsTimelineFragment extends TweetsListFragment {
    private TwitterClient client;
    //send an API request to get the timeline json
    //[] == JSONArray (ROOT)
    //{}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        populateTimeline(0L);
    }

    private void populateTimeline(Long max) {
        //Get Mention Time Line
        client.getMentionsTimeline(new JsonHttpResponseHandler() {
            //success
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG", json.toString());
                addAll(Tweet.fromJSONArray(json));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        }, max);
    }
}
