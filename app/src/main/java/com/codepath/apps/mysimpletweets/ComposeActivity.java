package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lzhan on 2/7/15.
 */
public class ComposeActivity extends ActionBarActivity {
    private String content;
    private TwitterClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        TextView tvUserScreenName = (TextView) findViewById(R.id.tvUserScreenName);
        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivUserImage);


        tvUserScreenName.setText("@"+ getIntent().getStringExtra("userscreenname"));
        ivProfileImage.setImageResource(android.R.color.transparent);
        Picasso.with(this).load(getIntent().getStringExtra("userimageurl")).into(ivProfileImage);

        int code = getIntent().getIntExtra("code", 0);

        final EditText etContent = (EditText) findViewById(R.id.etContent);
        Button btTweet = (Button) findViewById(R.id.btTweet);
        Button btCancel = (Button) findViewById(R.id.btCancel);

        btTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = etContent.getText().toString();
                if(!content.equals("")) {
                    client = TwitterApplication.getRestClient();
                    client.postStatusesUpdate(content, new JsonHttpResponseHandler() {
                        public void onSuccess(JSONObject json) {
                            //Intent intent = new Intent();
                            //setResult(RESULT_OK, intent);
                            //finish();
                        }
                    });

                }
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}
