package com.example.faigy.hala;

import android.util.Log;
import android.widget.Toast;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by Home on 2/18/2016.
 */
public class NewsJob extends Job {

    private final String TAG = "Import Job";

    public NewsJob() {
        super(new Params(5).requireNetwork());
    }

    @Override
    public void onAdded() {
        Log.d(TAG, "Import Job Added");
    }

    @Override
    public void onRun() throws Throwable {
        Log.d(TAG, "Import Job Job");
        Toast.makeText(Util.getContext(),"test",Toast.LENGTH_LONG).show();
        EventBus.getDefault().post(new JobEventBus("done"));

    }

    @Override
    protected void onCancel() {
        Log.d(TAG, "Import Job Cancelled");
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        Log.e(TAG, "Import Job Failed", throwable);
        return false;
    }

//
//    /**
//     * Function to create a location object from a JSONObject
//     *
//     * @param jsonLocation - JSONObject with location values
//     * @return location
//     */
//    public static News setLocation(JSONObject jsonLocation) {
//        // instantiate new location
//        News location = new News();
//
//        // set location values from corresponding JSONObject values
//        try {
//            // set id
//            location.setId(Integer.parseInt(
//                    jsonLocation.getString(Util.getStringValue(R.string.TAG__LOCATION_ID))));
//
//            // set branchId
//            location.setBranchId(Integer.parseInt(
//                    jsonLocation.getString(Util.getStringValue(R.string.TAG__BRANCH_ID))));
//
//            // set name
//            location.setName(jsonLocation.getString(Util.getStringValue(R.string.TAG_NAME)));
//
//            // set address
//            location.setAddress(getAddress(jsonLocation));
//
//            // set phone
//            location.setPhone(jsonLocation.getString(Util.getStringValue(R.string.TAG_PHONE)));
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        // return null if all crucial location information was not loaded
//        if (location.getId() == 0 || location.getBranchId() == 0
//                || location.getName() == null || location.getName().equals("null"))
//            return null;
//
//        return location;
//    }

}