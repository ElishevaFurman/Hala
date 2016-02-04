package com.example.faigy.hala;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Home on 2/3/2016.
 */
public class InTheNewsArticleFragment extends Fragment {
    // Declare controls
    private WebView mWebView;

    // Declare activities
    MainActivity mainActivity;

    public InTheNewsArticleFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_in_the_news_article, container, false);
        initializeViews(rootView);
        return rootView;

    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(final View rootView) {
        // initialize and reference controls
        mWebView = (WebView) rootView.findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(false);
        mWebView.loadUrl("http://m.jpost.com/In-Jerusalem/A-revolution-in-the-haredi-community-438940#article=6030QzIzMUJBMUZDNDcxNDFDQzNDRkVDMEE2M0I0NkU3MEQ=");
        mWebView.setWebViewClient(new WebViewClient());
    }
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}