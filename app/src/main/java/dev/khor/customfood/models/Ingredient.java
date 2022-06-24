package dev.khor.customfood.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Ingredient {
    private int id;
    private String name;
    private Boolean is_available;
    private Boolean is_obligatory;
    private int order;
    private ArrayList<Variation> variations;

    public int variationOrderList=0;

    public Ingredient(JSONObject response) {
        try{
            this.id =  response.getInt("id");
            this.name =  response.getString("name");
            this.is_available = response.getBoolean("is_available");
            this.is_obligatory = response.getBoolean("is_obligatory");
            this.order = response.getInt("order");
            JSONArray variations = response.getJSONArray("variations");
            this.variations = new ArrayList<Variation>();
            for(int i=0;i<variations.length();i++){
                this.variations.add(new Variation(variations.getJSONObject(i)));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getId(){ return id;}
    public String getName(){ return name;}
    public Boolean getIsAvaible(){ return is_available;}
    public Boolean getIs_obligatory(){ return is_obligatory;}
    public int getOrder(){ return order;}
    public ArrayList<Variation> getVariations(){ return variations;}

}
