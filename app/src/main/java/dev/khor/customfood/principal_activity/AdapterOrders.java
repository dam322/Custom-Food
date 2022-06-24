package dev.khor.customfood.principal_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.khor.customfood.R;
import dev.khor.customfood.models.Order;
import dev.khor.customfood.models.Product;

public class AdapterOrders extends RecyclerView.Adapter<AdapterOrders.OrdersViewHolder> {

    public ArrayList<Order> orders;
    public Activity activity;

    public AdapterOrders(ArrayList<Order> orders, Activity activity){
        this.orders= orders;
        this.activity= activity;
    }

    @NonNull
    @Override
    public AdapterOrders.OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrdersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrders.OrdersViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.idOrder.setText(orders.get(position).getIdOrder());
        holder.status.setText(orders.get(position).getStatus());
        holder.totalOrder.setText(Integer.toString(orders.get(position).getTotalOrders()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] opciones ={"Cancelar Pedido", "Volver atrás"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(activity);
                alertOpciones.setTitle("¿Qué desea realizar con el pedido: "+ orders.get(position).getIdOrder() + " ?");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(opciones[i].equals("Cancelar Pedido")){
                            if(orders.get(position).getStatus().equals("Pendiente")){
                                orders.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(activity,"Producto Eliminado Exitosamente", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(activity,"Error: El pedido está o ya fue procesado", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            dialogInterface.dismiss();
                        }
                    }
                });
                alertOpciones.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder {
        TextView idOrder;
        TextView status;
        TextView totalOrder;
        public OrdersViewHolder(@NonNull View v) {
            super(v);
            idOrder= (TextView) v.findViewById(R.id.idPedido);
            status= (TextView) v.findViewById(R.id.estadoPedido);
            totalOrder= (TextView) v.findViewById(R.id.totalPedido);
        }
    }
}
