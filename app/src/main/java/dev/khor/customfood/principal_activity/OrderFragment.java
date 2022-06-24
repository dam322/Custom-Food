package dev.khor.customfood.principal_activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dev.khor.customfood.DataContainer;
import dev.khor.customfood.R;
import dev.khor.customfood.models.Product;

public class OrderFragment extends Fragment implements View.OnClickListener {

    public RecyclerView order;
    public Activity activity;
    public View view;
    public AdapterOrder adapterOrder;
    public Button confirmOrder;

    public OrderFragment() { }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second, container, false);

        confirmOrder = (Button) view.findViewById(R.id.confirmOrderButton);
        confirmOrder.setOnClickListener(this);

        activity = getActivity();
        adapterOrder = new AdapterOrder(activity,DataContainer.orderProducts, view);

        order= (RecyclerView) view.findViewById(R.id.order_recycler_view);
        order.addItemDecoration(new DividerItemDecoration(order.getContext(), DividerItemDecoration.VERTICAL));
        order.setLayoutManager(new LinearLayoutManager(activity));
        order.setAdapter(adapterOrder);

        actualizarTotalPedido(view);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmOrderButton:
                final CharSequence[] opciones ={"Realizar el pedido", "Cancelar"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(activity);
                alertOpciones.setTitle("¿Está seguro que desea realizar el Pedido?");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(opciones[i].equals("Realizar el pedido")){
                            if(DataContainer.orderProducts.size()!=0){
                                for(int j=DataContainer.orderProducts.size();j>0;j--){
                                    DataContainer.orderProducts.remove(j-1);
                                }
                                adapterOrder.notifyDataSetChanged();
                                actualizarTotalPedido(view);
                                Toast.makeText(activity,"Pedido Realizado Exitosamente", Toast.LENGTH_SHORT).show();
                            }else{
                                dialogInterface.dismiss();
                                Toast.makeText(activity,"No hay elementos que ordenar", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            dialogInterface.dismiss();
                        }
                    }
                });
                alertOpciones.show();
                break;
        }
    }

    //---------------------------Actualizo precio -------------------------
    public static void actualizarTotalPedido(View v){
        TextView totalPedido = v.findViewById(R.id.costoTotal);
        int total =0;

        for (int i = 0;i<DataContainer.orderProducts.size();i++){
            total+=DataContainer.orderProducts.get(i).getPrice();
        }
        totalPedido.setText("$ "+Integer.toString(total));
        DataContainer.total=total;
    }
}