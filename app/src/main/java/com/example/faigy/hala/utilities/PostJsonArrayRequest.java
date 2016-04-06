package com.example.faigy.hala.utilities;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class PostJsonArrayRequest extends JsonRequest<JSONArray> {
    public final String KEY_ID = "id";
    public final String KEY_QUESTION = "question";
    public final String KEY_ANSWER = "answer";

    final String id = "12";
    final String question = "question2";
    final String answer = "answer2";

        /**
         * Creates a new request.
         *
         * @param url           URL to fetch the JSON from
         * @param listener      Listener to receive the JSON response
         * @param errorListener Error listener, or null to ignore errors.
         */
        public PostJsonArrayRequest(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
            super(Method.POST, "http://162.243.100.186/faq_test3.php" , null, listener, errorListener);
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put(KEY_ID, id);
                params.put(KEY_QUESTION, question);
                params.put(KEY_ANSWER, answer);
            return params;
        }

        @Override
        protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
            try {
                String jsonString =
                        new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                return Response.success(new JSONArray(jsonString),
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JSONException je) {
                return Response.error(new ParseError(je));
            }
        }
    }











//
////        public PostJsonArrayRequest(int method, String url, JSONObject jsonRequest,
////                Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
////            super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(),
////                    listener, errorListener);
////        }
//
//    public PostJsonArrayRequest(int method, String url, JSONObject jsonRequest,
//                                Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
//        super(Method.POST, url, null, listener, errorListener);
//    }
//
//        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(Util.getContext(), response, Toast.LENGTH_LONG).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Util.getContext(),error.toString(),Toast.LENGTH_LONG).show();
//                    }
//                }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//                params.put(KEY_ID, id);
//                params.put(KEY_QUESTION, question);
//                params.put(KEY_ANSWER, answer);
//                return params;
//            }
//
//        };
//    @Override
//    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
//        try {
//            String jsonString =
//                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//            return Response.success(new JSONArray(jsonString),
//                    HttpHeaderParser.parseCacheHeaders(response));
//        } catch (UnsupportedEncodingException e) {
//            return Response.error(new ParseError(e));
//        } catch (JSONException je) {
//            return Response.error(new ParseError(je));
//        }
//
//    }
//    RequestQueue requestQueue = Volley.newRequestQueue(Util.getContext());
//    requestQueue.add(stringRequest);


//    }

