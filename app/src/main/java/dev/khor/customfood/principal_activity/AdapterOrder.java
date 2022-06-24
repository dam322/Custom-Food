package dev.khor.customfood.principal_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.khor.customfood.DataContainer;
import dev.khor.customfood.R;
import dev.khor.customfood.login_activity.LoginActivity;
import dev.khor.customfood.models.Product;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.OrderViewHolder>{

    public Activity activity;
    public ArrayList<Product> products;
    public View v;

    public AdapterOrder(Activity activity, ArrayList<Product> products, View view){
        this.activity=activity;
        this.products=products;
        this.v= view;
    }

    @Override
    public OrderViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(products.get(position).getIsAvaible()){
            holder.is_avaible.setText("Disponible");
        }else{
            holder.is_avaible.setText("No Disponible");
        }
        holder.name.setText(products.get(position).getName());
        holder.price.setText(Integer.toString(products.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(products.get(position).getIsCustomizable()){
                    showDialog(products.get(position).getId(), position);
                }else{
                    Toast.makeText(activity,"El producto " + products.get(position).getName() +
                            " no es Personalizable", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView is_avaible;
        TextView name;
        TextView price;
        public OrderViewHolder (View v){
            super(v);

            is_avaible= (TextView) v.findViewById(R.id.is_avaibleProducto);
            name= (TextView) v.findViewById(R.id.nombreProducto);
            price= (TextView) v.findViewById(R.id.precioProducto);

        }
    }

    private void showDialog(int id, int orderList){
        PromptDialogFragment dialog = new PromptDialogFragment(id, orderList, activity, this, v);
        dialog.setCancelable(true);
        dialog.show(DataContainer.fm, "tag");

    }

    public static class PromptDialogFragment extends DialogFragment implements View.OnClickListener {
        ImageView btonClose;
        Button btonDelete, btonConfirm;

        public RecyclerView ingredient;
        public Activity activity;
        public View view;
        public View viewFragment;
        public AdapterIngredient adapterIngredient;
        public int id_product, orderList;
        public AdapterOrder adapter;

        public PromptDialogFragment(int id, int orderList, Activity activity, AdapterOrder adapter, View v){
            this.id_product = id;
            this.orderList= orderList;
            this.activity = activity;
            this.adapter = adapter;
            this.viewFragment=v;
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.order_ingredients, container, false);

            btonClose = view.findViewById(R.id.cancel_ingredients_button);
            btonClose.setOnClickListener(this);
            btonDelete = view.findViewById(R.id.deleteProductButton);
            btonDelete.setOnClickListener(this);
            btonConfirm = view.findViewById(R.id.confirmCustomizationButton);
            btonConfirm.setOnClickListener(this);
            
            adapterIngredient = new AdapterIngredient(activity, id_product);

            ingredient= (RecyclerView) view.findViewById(R.id.ingredients_recyclerView);
            ingredient.addItemDecoration(new DividerItemDecoration(ingredient.getContext(), DividerItemDecoration.HORIZONTAL));
            ingredient.setLayoutManager(new LinearLayoutManager(activity));
            ingredient.setAdapter(adapterIngredient);
            return view;
        }

        @Override
        public void onClick(View view) {
            if(view==btonClose){
                dismiss();
            }
            if(view==btonConfirm){
                dismiss();
            }
            if(view==btonDelete){
                DataContainer.orderProducts.remove(orderList);
                adapter.notifyDataSetChanged();
                OrderFragment.actualizarTotalPedido(viewFragment);
                dismiss();
            }
        }
    }
}