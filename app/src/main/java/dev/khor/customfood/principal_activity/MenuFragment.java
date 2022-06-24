package dev.khor.customfood.principal_activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import dev.khor.customfood.DataContainer;
import dev.khor.customfood.R;
import dev.khor.customfood.models.Product;
import dev.khor.customfood.network.NetworkManager;

public class MenuFragment extends Fragment {

    public RecyclerView menu;
    public Activity activity;
    public View view;
    public AdapterMenu adapterMenu;
    public NetworkListenerMenu networkListenerMenu;

    public MenuFragment() { }

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        networkListenerMenu = new NetworkListenerMenu();
        ArrayList<String> body = new ArrayList<String>();

        try {
            NetworkManager.getInstance(this.getContext()).listProduct(
                    networkListenerMenu.listenerResponse,
                    networkListenerMenu.listenerError,
                    body
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_third, container, false);
        activity = getActivity();

        adapterMenu = new AdapterMenu(activity,DataContainer.products);

        menu= (RecyclerView) view.findViewById(R.id.menu_recycler_view);
        //decorador
        menu.addItemDecoration(new DividerItemDecoration(menu.getContext(), DividerItemDecoration.VERTICAL));
        menu.setLayoutManager(new LinearLayoutManager(activity));
        menu.setAdapter(adapterMenu);

        return view;
    }

    private static class NetworkListenerMenu {

        public String TAG = "Principal Activity";

        public final Response.Listener<JSONArray> listenerResponse = response -> {
            DataContainer.products = new ArrayList<>();

            Log.d(TAG + ": ", "Success Response : " + response.toString());
            try {
                for (int i = 0; i < response.length(); i++) {
                    DataContainer.products.add(new Product(response.getJSONObject(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        public final Response.ErrorListener listenerError = error -> {
            Log.d(TAG + ": ", "Error Response : " + error.toString());
        };
    }
}