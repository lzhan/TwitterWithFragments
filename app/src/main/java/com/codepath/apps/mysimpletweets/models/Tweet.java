package com.codepath.apps.mysimpletweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by lzhan on 2/4/15.
 */

//parse the json, store the data
//encapsulate state logic or display logic
public class Tweet {
    //list out the attributes
    private String body;
    private Long uid;  //unique id for the tweet
    private User user;
    private String createdAt;

    public User getUser() { return user; }

    public String getBody() {
        return body;
    }

    public Long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // Deserialize the JSON and build Tweet
    public static Tweet fromJSON(JSONObject jsonObject) {
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweet;
    }
    //pass in json array, output list of tweet
    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i< jsonArray.length(); i++) {
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                if(tweet != null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return tweets;
    }
}
