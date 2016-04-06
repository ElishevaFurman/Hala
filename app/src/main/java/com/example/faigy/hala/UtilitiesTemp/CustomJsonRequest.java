package com.example.faigy.hala.UtilitiesTemp;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Home on 3/29/2016.
 */

    public class CustomJsonRequest extends Request {

        public final String KEY_ID = "id";
        public final String KEY_QUESTION = "question";
        public final String KEY_ANSWER = "answer";
        public final String id = "13";
        public final String question = "question2";
        public final String answer = "answer2";

        Map<String, String> params;
        private Response.Listener listener;

        public CustomJsonRequest(int requestMethod, String url, Map<String, String> params,
                                 Response.Listener responseListener, Response.ErrorListener errorListener) {

            super(requestMethod, url, errorListener);


            params = new HashMap<String, String>();
            params.put(KEY_ID, id);
            params.put(KEY_QUESTION, question);
            params.put(KEY_ANSWER, answer);
            this.params = params;
            this.listener = responseListener;
        }

        @Override
        protected void deliverResponse(Object response) {
            listener.onResponse(response);

        }

        @Override
        public Map<String, String> getParams() throws AuthFailureError {

            return params;
        }

        @Override
        protected Response parseNetworkResponse(NetworkResponse response) {
            try {
                String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                return Response.success(new JSONObject(jsonString),
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JSONException je) {
                Toast.makeText(Util.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                return Response.error(new ParseError(je));
            }
        }
    }

