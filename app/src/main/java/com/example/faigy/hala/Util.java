package com.example.faigy.hala;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 1/28/2016.
 */
public class Util extends Activity {
    // initialize variables
    private static Context context = null;
    private static Activity activity = null;
    private static Application application = null;


    /**
     * Function to set reference of current activity
     *
     * @param activity - current activity
     */
    public static void setActivity(Activity activity) {
        // set this activity to current activity
        Util.activity = activity;
    }

    /**
     * Function to return reference of current activity
     *
     * @return activity
     */
    public static Activity getActivity() {
        return activity;
    }

    /**
     * Function to return reference of current context
     *
     * @return context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * Function to set reference of current context
     *
     * @param context - current context
     */
    public static void setContext(Context context) {

        // set this context to current context
        Util.context = context;
    }



    /**
     * Function to call methods that set reference to current activity
     *
     * @param activity - current activity
     */
    public static void setReference(Activity activity) {
        // set context to activity
        setContext(activity);
        // set activity to activity
        setActivity(activity);
    }

    /**
     * Function to add / replace fragment
     *
     * @param fragment - new fragment
     * @param tag      - tag to add along with the fragment to the back stack
     */
    public static void replaceFragment(Fragment fragment, int tag) {
        // replace fragment in container
        getActivity().getFragmentManager().beginTransaction().replace(R.id.container,
                fragment).addToBackStack(tag + "").commit();

    }



    /**
     * Function to set title of toolbar
     *
     * @param toolbarTitle - int of string reference to add as toolbar title
     * @param toolbar      - set title to this toolbar
     */
    public static void setToolbarTitle(int toolbarTitle, Toolbar toolbar) {
        // set toolbar title
        toolbar.setTitle(toolbarTitle);
    }

    /**
     * Function to set title of toolbar
     *
     * @param toolbarTitle - String of string reference to add as toolbar title
     * @param toolbar      - set title to this toolbar
     */
    public static void setToolbarTitle(String toolbarTitle, Toolbar toolbar) {
        // set toolbar title
        toolbar.setTitle(toolbarTitle);
    }

    /**
     * @param icon    - drawable for the toolbar icon
     * @param toolbar - set the icon to this toolbar
     */
    public static void enableBackButton(int icon, Toolbar toolbar, DrawerLayout drawer) {
        toolbar.setNavigationIcon(icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getFragmentManager().popBackStack();
            }
        });

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    /**
     * @param icon    - drawable for the toolbar icon
     * @param toolbar - set the icon to this toolbar
     */
    public static void setNavigationIcon(int icon, Toolbar toolbar, DrawerLayout drawer) {
        toolbar.setNavigationIcon(icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //activity.getFragmentManager().popBackStack();

            }
        });

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    /**
     * Function to hide keyboard
     */
    public static void hideSoftKeyboard() {
        // get instance of keyboard through InputMethodManager
        InputMethodManager inputMethodManager = (InputMethodManager)
                Util.getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);

        // find the currently focused view, so we can get the correct window token from it.
        View view = Util.getActivity().getCurrentFocus();

        // if no view currently has focus, create a new one, so we can grab a window token from it
        if (view == null) {
            view = new View(Util.getActivity());
        }

        // hide keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    /**
     * Function to open Google Maps with address populated
     *
     * @param address - address that is added to Uri for intent
     */
    public static void navigationIntent(String address) {
        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address);

        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        // Attempt to start an activity that can handle the Intent
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            activity.startActivity(mapIntent);
        }
    }


    /**
     * Function to make a call with an intent
     *
     * @param number - number to call
     */
    public static void callIntent(String number) {
        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri call = Uri.parse("tel:" + number);
        // Create an Intent. Set the action to ACTION_CALL and send uri
        Intent intent = new Intent(Intent.ACTION_CALL, call);

        // Attempt to start an activity that can handle the Intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            activity.startActivity(intent);
        } else {
            // Show message: "No phone clients installed."
            Toast.makeText(context, "No phone clients installed.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Function to send email with an intent
     *
     * @param addresses - array of email addresses
     * @param subject   - subject line of email
     * @param text      - text for the body of the email
     */
    public static void composeEmail(String[] addresses, String subject, String text) {
        // Create an Intent. Set the action to ACTION_SENDTO
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        // Only email apps should handle this
        // Create a Uri from an intent string.
        intent.setData(Uri.parse("mailto:"));
        if (addresses != null) {
            // Add addresses to intent
            intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        }
        // Add subject to intent
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (text != null) {
            // Add subject to intent
            intent.putExtra(Intent.EXTRA_TEXT, text);
        }
        // Attempt to start an activity that can handle the Intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            activity.startActivity(intent);
        } else {
            // Show message: "No email clients installed."
            Toast.makeText(context, "No email clients installed.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Function to open link of article in browser
     */
    public static void openUrlInBrowser(String url) {
        getActivity().startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(url)));

    }

    public static void share(String link) {
        List<Intent> targetedShareIntents = new ArrayList<Intent>();
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        List<ResolveInfo> resInfo = activity.getPackageManager().queryIntentActivities(shareIntent, 0);
        if (!resInfo.isEmpty()) {
            for (ResolveInfo resolveInfo : resInfo) {
                String packageName = resolveInfo.activityInfo.packageName;
                Intent targetedShareIntent = new Intent(android.content.Intent.ACTION_SEND);
                targetedShareIntent.setType("text/plain");
                targetedShareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "hala article");
                if (TextUtils.equals(packageName, "com.facebook.katana")) {
                    targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, link);
                } else {
                    targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, link);
                }
                targetedShareIntent.setPackage(packageName);
                targetedShareIntents.add(targetedShareIntent);
            }
            Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "Select app to share");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
            activity.startActivity(chooserIntent);
        }
    }

    /**
     * @param title              - set dialog title
     * @param message            - set dialog message
     * @param positiveButtonText - set text for positive button in dialog
     * @param negativeButtonText - set text for negative butoon in dialog
     * @param tag                - set tag for listener in dialog
     * @param param              - set param for listener in dialog
     */
    public static void createDialog(String title, String message, String positiveButtonText,
                                    String negativeButtonText, final String tag, final String param) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(Util.getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (tag) {

                    case "call":
                        callIntent(param);
                        break;
                    case "url":
                        openUrlInBrowser(param);
                        break;
                    default:
                        break;

                }

            }
        });


        builder.setNegativeButton(negativeButtonText, null);
        builder.show();

    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * Function to return string from resource
     *
     * @param resourceReference - reference to position in resource file
     * @return String
     */
    public static String getStringValue(int resourceReference) {
        // get string from resource and return
        return context.getString(resourceReference);
    }


    /**
     * Function to call http request that return a JsonObject and then convert it to a JsonArray
     *
     * @param url    - url to get the JSON string from
     * @param tag    - tag of node to get objects from
     * @param params - list of key values to pass along to the http request
     * @return JsonArray
     */
    public static JSONArray getJsonArray(String url, String tag, List<NameValuePair> params) {
        // instantiate new JsonArray
        JSONArray jsonArray = new JSONArray();

        // instantiate new JsonParser
        JSONParser jsonParser = new JSONParser();

        // create JSON Object from request made to url
        JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

        // initialize int to receive value of success (0 or 1)
        int success = 0;

        try {
            // get int with tag "success" from json object
            success = json.getInt("success");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // if success is 0 ; either because the query of the http request was not successful
        // or because the http request itself was unsuccessful and therefor the JsonObject is null
        if (success != 0) {
            try {
                // get JSON Array node
                jsonArray = json.getJSONArray(tag);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            // could not get JSON Array node
            Log.e("Json Parser", "Couldn't get any newsData from the url");
        }

        return jsonArray;
    }

}


