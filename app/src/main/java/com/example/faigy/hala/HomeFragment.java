package com.example.faigy.hala;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.path.android.jobqueue.JobManager;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import de.greenrobot.event.EventBus;

/**
 * Created by Home on 1/21/2016.
 */
public class HomeFragment extends Fragment {
    MainActivity mainActivity;
    // Declare Controls
    LinearLayout aboutLinearLayout, servicesLinearLayout, contactLinearLayout;
    ImageView homeImageView;
    // json array response url
    private String urlJsonArry = "http://162.243.100.186/news_array.php";

    //private String urlJsonArry = "http://api.androidhive.info/volley/person_array.json";


    JSONArray response;
    News[] data;
    ArrayList<News> newsList;

    private static String TAG = MainActivity.class.getSimpleName();
    private Button btnMakeArrayRequest;


    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;

    public HomeFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

    }

        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the views for this fragment
        initializeViews(rootView);
        registerListeners();
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_home, mainActivity.toolbar);
        // remove keyboard from screen
        Util.hideSoftKeyboard();
        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_home);
        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        EventBus.getDefault().post(new DownloadDataEvent("hi"));
        aboutLinearLayout = (LinearLayout) rootView.findViewById(R.id.aboutLinearLayout);
        servicesLinearLayout = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout);
        contactLinearLayout = (LinearLayout) rootView.findViewById(R.id.contactLinearLayout);
        homeImageView = (ImageView) rootView.findViewById(R.id.homeImageView);
        homeImageView.setImageBitmap(
                Util.decodeSampledBitmapFromResource(getResources(), R.drawable.pink_ribbon_background, 180, 180));

    }








    public void registerListeners() {
        aboutLinearLayout.setOnClickListener(aboutLinearLayoutListener);
        servicesLinearLayout.setOnClickListener(servicesLinearLayoutListener);
        contactLinearLayout.setOnClickListener(contactLinearLayoutListener);
    }

    /**
     * OnClickListener for aboutLinearLayoutListener
     */
    View.OnClickListener aboutLinearLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Util.replaceFragment(mainActivity.aboutUsFragment, R.string.fragment_about);
            //set navigation selected to current fragment
            mainActivity.setSelectedNavigationItem(R.id.nav_about);
        }
    };

    /**
     * OnClickListener for servicesLinearLayoutListener
     */
    View.OnClickListener servicesLinearLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Util.replaceFragment(mainActivity.servicesFragment, R.string.fragment_services);
            //set navigation selected to current fragment
            mainActivity.setSelectedNavigationItem(R.id.nav_services);
        }
    };

    /**
     * OnClickListener for contactLinearLayoutListener
     */
    View.OnClickListener contactLinearLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Util.replaceFragment(mainActivity.contactUsFragment, R.string.fragment_contact);
            //set navigation selected to current fragment
            mainActivity.setSelectedNavigationItem(R.id.nav_contact);


        }
    };


    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    // This method will be called when a HelloWorldEvent is posted
    public void onEvent(DownloadDataEvent event){
        // your implementation
        //Toast.makeText(Util.getContext(), event.getTag(), Toast.LENGTH_LONG).show();
       event.download();
    }

    @Override
    public void onPause() {

        EventBus.getDefault().unregister(this);
        super.onPause();
    }

}
