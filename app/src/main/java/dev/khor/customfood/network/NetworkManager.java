package dev.khor.customfood.network;

import android.content.Context;
import android.media.session.MediaSession;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dev.khor.customfood.DataContainer;

public class NetworkManager {
    private static NetworkManager instance = null;
    private static final String API_URL = "https://pedidos-custom.herokuapp.com/";

    public RequestQueue requestQueue;

    private NetworkManager(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized NetworkManager getInstance(Context context) {
        if (instance == null)
            instance = new NetworkManager(context);
        return instance;
    }

    public void login(
            final Listener<JSONObject> listenerResponse,
            final ErrorListener listenerError,
            final Map<String, Object> body) {
        String url = API_URL + "login/";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                new JSONObject(body),
                listenerResponse,
                listenerError);
        requestQueue.add(request);
    }

    public void signup(
            final Listener<JSONObject> listenerResponse,
            final ErrorListener listenerError,
            final Map<String, Object> body) {
        String url = API_URL + "signup/";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                new JSONObject(body),
                listenerResponse,
                listenerError){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/json");
                        return params;
                    }
                };
        requestQueue.add(request);
    }

    public void modifyUser(
            final int id,
            final Listener<JSONObject> listenerResponse,
            final ErrorListener listenerError,
            final Map<String, Object> body) {
        String url = API_URL + "account/"+ Integer.toString(id);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                new JSONObject(body),
                listenerResponse,
                listenerError){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String credentials = "david@admin.com:terry0718";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);
                return params;
            }
        };
        requestQueue.add(request);
    }

    public void listProduct(
            final Listener<JSONArray> listenerResponse,
            final ErrorListener listenerError,
            final ArrayList<String> body) throws JSONException {
        String url = API_URL + "products/";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                new JSONArray(body),
                listenerResponse,
                listenerError){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String credentials = "david@admin.com:terry0718";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);
                return params;
            }
        };
        requestQueue.add(request);
    }

    /*
        Cuando necesiten hacer una petición crean un método basado en el método login. Cambian
        la variable url y hacen las modificaciones necesarias.
    */
}
