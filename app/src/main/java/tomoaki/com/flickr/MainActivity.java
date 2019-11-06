package tomoaki.com.flickr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
{
    String FlickrQuery_url = "https://api.flickr.com/services/feeds/photos_public.gne?format=json &nojsoncallback=1 ";
    String API_KEY = "92fbd15c1f7cf45061c6463e8bade041";
    String FlickrQuery_per_page = "&per_page=1";
    String SECRET = "de0fd73f6ea4da3c";
    String FlickrQuery_key = "&api_key=";
    final String LOGCAT_TAG = "flickr";
    String FlickrQuery_format = "&format=json";

    Button mButton;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.button);
        mTextView = findViewById(R.id.textView);

        final RequestParams params = new RequestParams();
        params.put(FlickrQuery_key, API_KEY);

        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                networking(params);
            }
        });


    }

    private void networking(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(FlickrQuery_url+FlickrQuery_format, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d(LOGCAT_TAG, "onSuccess JSON: "+response);
                Model link = Model.fromJson(response);
                updateUI(link);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                Log.d(LOGCAT_TAG, "onFailure fail: "+e.toString());
                Log.d(LOGCAT_TAG, "onFailure status: "+statusCode);
                Toast.makeText(MainActivity.this,"JSON Requested Fail",Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void updateUI(Model data) {
        Log.d(LOGCAT_TAG, "updateUI: " + data.getLink());
       mTextView.setText(data.getLink());

    }
}
