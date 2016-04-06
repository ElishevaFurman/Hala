package com.example.faigy.hala.Utilities;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.faigy.hala.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Util extends Activity {

    // initialize variables
    private static Context context = null;
    private static Activity activity = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
                fragment, tag + "").addToBackStack(tag + "").commit();

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
     * @param toolbar - set the icon to this toolbar
     * @param drawer  - navigation drawer
     */
    public static void enableBackButton(Toolbar toolbar, DrawerLayout drawer) {
        // if the language of the phone is hebrew
        if (!Locale.getDefault().getLanguage().equals("en")) {
            // set arrow to forward arrow
            toolbar.setNavigationIcon(R.drawable.ic_arrow_forward_24dp);
        }
        // if language of of the phone is english
        else {
            // set arrow to back arrow
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pop the backStack
                activity.getFragmentManager().popBackStack();
            }
        });

        // set drawerLockMode to LOCK_MODE_LOCKED_CLOSED
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
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
    public static void navigationIntent(String address, String tag) {

        if (tag.equals("googleMaps")){
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
        }else{
            //for waze
            if (isPackageInstalled("com.waze", getContext())){
                try {

                    String url = "waze://?q="+address;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    activity.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    Intent intent =
                            new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"));
                    activity.startActivity(intent);
                }
            }else {
                Toast.makeText(getContext(),R.string.error_waze_not_installed, Toast.LENGTH_LONG).show();
            }

        }

    }

    /**
     *
     * @param packageName - name of package
     * @param context - activity's context
     * @return boolean
     */
    private static boolean isPackageInstalled(String packageName, Context context) {
        // istanitiate pm
        PackageManager pm = context.getPackageManager();
        try {
            // try to get packageInfo of pm
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            // return true
            return true;
        // if no information about the package was found
        } catch (PackageManager.NameNotFoundException e) {
            // return false
            return false;
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

    public static void emailHala() {

        // get list of email addresses from xml array
        String[] addresses =
                Util.getActivity().getResources().getStringArray(R.array.addresses);

        // subject line
        String subject = "Contact Hala";
        // compose email using email address and subject line
        Util.composeEmail(addresses, subject, null);
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

    /**
     * Function to open link of article in browser
     */
    public static void openWifiSettings() {
        getActivity().startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

    }

    /**
     * Function to share link of article
     *
     * @param link - link of article
     */
    public static void share(String link) {
        // instanitiate targetedShareIntents
        List<Intent> targetedShareIntents = new ArrayList<>();
        // instanitiate shareIntent and set the action to ACTION_SEND
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        // set type of shareIntent to text/plain
        shareIntent.setType("text/plain");
        // instanitiate resInfo and set it to the phone's packages managers intent activities
        List<ResolveInfo> resInfo = activity.getPackageManager()
                .queryIntentActivities(shareIntent, 0);
        // if resInfo is not empty
        if (!resInfo.isEmpty()) {
            // loop through each item in resInfo
            for (ResolveInfo resolveInfo : resInfo) {
                // assign resolveInfo.activityInfo.packageName to packageName
                String packageName = resolveInfo.activityInfo.packageName;
                // instanitiate targetedShareIntent and set the action to ACTION_SEND
                Intent targetedShareIntent = new Intent(Intent.ACTION_SEND);
                // set type of targetedShareIntent to text/plain
                targetedShareIntent.setType("text/plain");
                // add subject line to intent
                targetedShareIntent.putExtra(Intent.EXTRA_SUBJECT, "hala article");
                // if textUtils is equals to facebook
                if (TextUtils.equals(packageName, "com.facebook.katana")) {
                    // add to intent the link
                    targetedShareIntent.putExtra(Intent.EXTRA_TEXT, link);
                } else {
                    // add to intent the link
                    targetedShareIntent.putExtra(Intent.EXTRA_TEXT, link);
                }
                // set package of targetedShareIntent to packageName
                targetedShareIntent.setPackage(packageName);
                // add targetedShareIntent
                targetedShareIntents.add(targetedShareIntent);
            }
            // instanitiate chooserIntent and assign to it a list of app to choose from with
            // the title "Select app to share"
            Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0),
                    "Select app to share");
            // add EXTRA_INITIAL_INTENTS tp chooserIntent and get item from array
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    targetedShareIntents.toArray(new Parcelable[]{}));
            // start chooserIntent
            activity.startActivity(chooserIntent);
        }
    }

    /**
     * @param title              - set dialog title
     * @param message            - set dialog message
     * @param positiveButtonText - set text for positive button in dialog
     * @param negativeButtonText - set text for negative button in dialog
     * @param neutralButtonText - set text for neutral button in dialog
     * @param tag                - set tag for listener in dialog
     * @param param              - set param for listener in dialog
     */
    public static void createDialog(int title, int message, int positiveButtonText,
                                    int negativeButtonText, String neutralButtonText, final String tag, final String param) {
        // initialize builder and set it to AlertDialog.Builder of style AppCompatAlertDialogStyle
        AlertDialog.Builder builder =
                new AlertDialog.Builder(Util.getActivity(), R.style.AppCompatAlertDialogStyle);
        // set title of builder with title param
        builder.setTitle(title);
        // set message of builder message param
        builder.setMessage(message);
        // set positive button of builder
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
                    case "internet":
                        openWifiSettings();
                        break;
                    case "email":
                        emailHala();
                        break;
                    case "bus":
                        openUrlInBrowser(param);
                        break;
                    case "maps":
                        navigationIntent(param ,"googleMaps");
                        break;
                    default:
                        break;
                }

            }
        });

        builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (tag.equals("maps")){
                    navigationIntent(param, "waze");
                }
            }
        });
        // set negative button of builder with negativeButtonText param
        builder.setNeutralButton(neutralButtonText, null);
        // show builder
        builder.show();

    }

    /**
     * Function to get the size of an image
     *
     * @param options   - of type BitmapFactory.options
     * @param reqWidth  - set requested width of image
     * @param reqHeight - set requested height of image
     * @return sample size of image
     */
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

    /**
     * Function to decode bitmap sample size and set it
     *
     * @param res       of type Resources
     * @param resId     of type int
     * @param reqWidth  - width of image
     * @param reqHeight - height of image
     * @return Bitmap
     */
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
     * Function to check internet connection status
     *
     * @return boolean if connected true else false
     */
    public static boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        // check if there is connection
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return true;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return true;
        }
        return false;
    }

    /**
     * Function to handle error exceptions for making a request to download data
     *
     * @param error         - set error
     * @param errorTextView - set textView to display error message
     */
    public static void handleVolleyError(VolleyError error, TextView errorTextView) {
        errorTextView.setVisibility(View.VISIBLE);
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            errorTextView.setText(R.string.error_no_connection_found);
        } else if (error instanceof AuthFailureError) {
            errorTextView.setText(R.string.error_authentication_failed);
        } else if (error instanceof ServerError) {
            errorTextView.setText(R.string.error_server_error);
        } else if (error instanceof NetworkError) {
            errorTextView.setText(R.string.error_network_error);
        } else if (error instanceof ParseError) {
            errorTextView.setText(R.string.error_parsing_error);
        }
    }

    public static ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog dialog = new ProgressDialog(mContext);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.progressdialog);
        // dialog.setMessage(Message);
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Util Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.faigy.hala.Utilities/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Util Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.faigy.hala.Utilities/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}


