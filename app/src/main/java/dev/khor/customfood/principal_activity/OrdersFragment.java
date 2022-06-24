package dev.khor.customfood.principal_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;

import dev.khor.customfood.DataContainer;
import dev.khor.customfood.R;
import dev.khor.customfood.models.Order;

public class OrdersFragment extends Fragment {

    public RecyclerView orders;
    public Activity activity;
    public View view;
    public AdapterOrders adapterOrders;
    ArrayList<Order> listOrders;

    public OrdersFragment() {
    }


    public static OrdersFragment newInstance() {
        return new OrdersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_orders, container, false);
        activity=getActivity();

        listOrders = generarPedido();

        adapterOrders = new AdapterOrders(listOrders, activity);

        orders= (RecyclerView) view.findViewById(R.id.orders_recyclerview);
        orders.addItemDecoration(new DividerItemDecoration(orders.getContext(), DividerItemDecoration.VERTICAL));
        orders.setLayoutManager(new LinearLayoutManager(activity));
        orders.setAdapter(adapterOrders);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<Order> generarPedido(){
        ArrayList<Order> listOrders= new ArrayList<Order>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy_hh:mm");
        Calendar c = Calendar.getInstance();
        String date = sdf.format(c.getTime());
        listOrders.add(new Order(date +"_"+DataContainer.user.getFirstName(),"Pendiente",DataContainer.total));
        listOrders.add(new Order(date +"_"+DataContainer.user.getFirstName(),"Entregado",DataContainer.total));
        return listOrders;
    }

}