package com.example.faigy.hala;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;

import de.greenrobot.event.EventBus;


/**
 * Created by Home on 2/9/2016.
 */
public class AboutUsFragment extends Fragment {

    // Declare Activities
    MainActivity mainActivity;

    public AboutUsFragment() {
        // Required empty public constructor
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
        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);
        // Initialize the views for this fragment
        initializeViews(rootView);
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_about, mainActivity.toolbar);
        // remove keyboard from screen
        Util.hideSoftKeyboard();
        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_about);
        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        EventBus.getDefault().post(new DownloadDataEvent(""));
        //int size = MySingleton.getInstance().getNewsArrayList().size();
        //Toast.makeText(Util.getContext(),size+"",Toast.LENGTH_LONG).show();
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    // This method will be called when a HelloWorldEvent is posted
    public void onEvent(DownloadDataEvent event) {
        event.download();
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }
}