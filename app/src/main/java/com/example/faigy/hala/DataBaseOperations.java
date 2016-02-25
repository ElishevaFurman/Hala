package com.example.faigy.hala;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Home on 2/23/2016.
 */
public class DataBaseOperations {
    Context context;
    public DataBaseOperations(Context context){
    this.context = context;
    }

    // json array response url
    //private String urlJsonArry = "http://162.243.100.186/news_array.php";
    // Declare Variables for newsclass
    News[] newsData;
    ArrayList<News> newsList;

    TeamMember[] teamData;
    ArrayList<TeamMember> teamList;

    MainActivity mainActivity;

    private static String TAG = MainActivity.class.getSimpleName();



    /**
     * Method to make json array request where response starts with
     * */
    //public void makeJsonArrayRequest(String url, final ArrayList<?> arrayList, final List[] array) {
    public void makeJsonArrayRequest(final String tag, String urlJsonArry) {

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new Gson();
                        String jsonOutput = response.toString();
                        try {
                            if (tag.equals("news")){
                                newsData = gson.fromJson(jsonOutput, News[].class);
                                newsList = new ArrayList<>(Arrays.asList(newsData));
                                MySingleton.getInstance().setNewsArrayList(newsList);
                            } else if (tag.equals("members")){
                                teamData = gson.fromJson(jsonOutput, TeamMember[].class);
                                teamList = new ArrayList<>(Arrays.asList(teamData));
                                MySingleton.getInstance().setTeamMembersArrayList(teamList);
                            }

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(Util.getContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(req);

    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
