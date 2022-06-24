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

import dev.khor.customfood.DataContainer;
import dev.khor.customfood.R;
import dev.khor.customfood.models.Product;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.MenuViewHolder> {

    public Activity activity;
    public ArrayList<Product> products;

    public AdapterMenu(Activity activity, ArrayList<Product> products){
        this.activity=activity;
        this.products=products;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, @SuppressLint("RecyclerView") final int position) {
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
                final CharSequence[] opciones ={"Añadir a la cola de pedido", "Cancelar"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(activity);
                alertOpciones.setTitle("¿Desea agregar el producto "+products.get(position).getName()+ " a la cola de pedido?");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(opciones[i].equals("Añadir a la cola de pedido")){
                            addProduct(products.get(position));
                            Toast.makeText(activity,"Producto añadido exitosamente", Toast.LENGTH_SHORT).show();
                        }else{
                            dialogInterface.dismiss();
                        }
                    }
                });
                alertOpciones.show();
            }
        });
    }

    public void addProduct(Product p){
        if(p.getIsAvaible()){
            DataContainer.orderProducts.add(p);
        }else{
            Toast.makeText(activity,"El producto actualmente no está disponible", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder{
        TextView is_avaible;
        TextView name;
        TextView price;
        //ArrayList<TextView> ingredients;
        //ArrayList<TextView> variations;
        public MenuViewHolder(View v){
            super(v);

            is_avaible= (TextView) v.findViewById(R.id.is_avaibleProducto);
            name= (TextView) v.findViewById(R.id.nombreProducto);
            price= (TextView) v.findViewById(R.id.precioProducto);

        }
    }
}
