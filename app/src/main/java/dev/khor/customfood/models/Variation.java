package dev.khor.customfood.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Variation {

    private int id;
    private String name;
    private Boolean is_available;

    public Variation(JSONObject response) {
        try{
            this.id =  response.getInt("id");
            this.name =  response.getString("name");
            this.is_available = response.getBoolean("is_available");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getId(){ return id;}
    public String getName(){ return name;}
    public Boolean getIsAvaible(){ return is_available;}
}
