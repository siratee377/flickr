package tomoaki.com.flickr;

import org.json.JSONException;
import org.json.JSONObject;

public class Model
{
    private String link;

    public static Model fromJson(JSONObject jsonObject) {

        Model Data = new Model();
        try {
            Data.link = jsonObject.getString("link");

            return Data;


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getLink()
    {
        return link;
    }
}
