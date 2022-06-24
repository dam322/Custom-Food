package dev.khor.customfood.models;

import android.media.Image;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Product {
    private int id;
    private String name;
    private Boolean is_customizable;
    private Boolean is_available;
    private String photo;
    private int price;
    private ArrayList<Ingredient> ingredients;

    public Product(JSONObject response) {

        try{
            this.id= response.getInt("id");
            this.name =  response.getString("name");
            this.price = response.getInt("price");
            this.is_customizable = response.getBoolean("is_customizable");
            this.is_available = response.getBoolean("is_available");
            this.photo=response.getString("photo");
            JSONArray ingredients = response.getJSONArray("ingredients");
            this.ingredients = new ArrayList<Ingredient>();
            for(int i=0;i<ingredients.length();i++){
                this.ingredients.add(new Ingredient(ingredients.getJSONObject(i)));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public int getId(){return id;}

    public int getPrice(){return price;}

    public void setEmail(String name) {
        this.name = name;
    }

    public Boolean getIsCustomizable() {
        return is_customizable;
    }

    public void setType(Boolean is_customizable) {
        this.is_customizable = is_customizable;
    }

    public Boolean getIsAvaible() {
        return is_available;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setFirstName(Boolean is_avaible) {
        this.is_available = is_avaible;
    }


    @NonNull
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", is_customizable='" + is_customizable.toString() + '\'' +
                ", is_avaible='" + is_available.toString() + '\'' +
                '}';
    }
}
