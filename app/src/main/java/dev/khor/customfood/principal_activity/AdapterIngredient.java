package dev.khor.customfood.principal_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.khor.customfood.DataContainer;
import dev.khor.customfood.R;
import dev.khor.customfood.models.Ingredient;

public class AdapterIngredient extends RecyclerView.Adapter<AdapterIngredient.IngredientViewHolder>{

    public Activity activity;
    public int id_product;
    public int item_count=0;
    private ArrayList<Ingredient> ingredients= new ArrayList<>();

    public AdapterIngredient(Activity activity, int id_product){
        this.activity= activity;
        this.id_product=id_product;

        for (int i=0; i< DataContainer.products.size();i++){
            if(DataContainer.products.get(i).getId() == id_product){
                ingredients= DataContainer.products.get(i).getIngredients();
                item_count=  DataContainer.products.get(i).getIngredients().size();
            }
        }
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new IngredientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterIngredient.IngredientViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(ingredients.get(position).getIsAvaible()){

            holder.nombre.setText(ingredients.get(position).getName());

            int variations_sizeArray = ingredients.get(position).getVariations().size();
            List<String> arrayVariantes = new ArrayList<String>();

            for (int i=0; i<variations_sizeArray;i++){
                if (ingredients.get(position).getVariations().get(i).getIsAvaible()){
                    arrayVariantes.add(ingredients.get(position).getVariations().get(i).getName());
                }
            }
            //Añadir items al Spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity.getApplicationContext(),
                    R.layout.spinner_textview_white, arrayVariantes);
            adapter.setDropDownViewResource(R.layout.spinner_textview_dark);
            holder.variantes.setAdapter(adapter);
            //item listener
            holder.variantes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> spn, android.view.View v, int posicion, long id){
                    ingredients.get(position).variationOrderList=posicion;
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    ingredients.get(position).variationOrderList=0;
                }
            });

            holder.variantes.setSelection(ingredients.get(position).variationOrderList);

        }
    }

    @Override
    public int getItemCount() {
        return item_count;
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        Spinner variantes;
        public IngredientViewHolder(@NonNull View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_ingrediente);
            variantes = (Spinner) v.findViewById(R.id.spinner_variantes);
        }
    }
}
