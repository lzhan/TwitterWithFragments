package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by lzhan on 2/4/15.
 */

//taking the tweet object and turn it into views
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, android.R.layout.simple_list_item_1, tweets);
    }

    //override and setup custom template
    //ViewHolder Pattern
    //http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#improving-performance-with-the-viewholder-pattern

    public View getView(int position, View convertView, ViewGroup parent) {
        Tweet tweet = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        //Typeface type = Typeface.createFromAsset(getContext().getAssets(),"fonts/DroidSansFallback.ttf");
        //tvName.setTypeface(type);

        tvUserName.setText(tweet.getUser().getName());
        tvName.setText("@"+tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());

        String date = tweet.getCreatedAt();
        String dateFormat="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        Date d = new Date();
        try {
            d = sf.parse(date);
        } catch (ParseException e){
            e.printStackTrace();
        }
        int timediff = (int) (System.currentTimeMillis()-d.getTime())/60000;
        int hours = timediff / 60;
        if(hours != 0) {
            tvTime.setText(hours+"h");
        } else {
            tvTime.setText(timediff%60+"m");
        }
        //tvTime.setText(Long.toString(timediff));
        //clear out the old image for a recycled view
        ivProfileImage.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);
        return convertView;
    }
}
