package dev.khor.customfood.models;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Restaurant {
    private int id;
    private String direction;
    private String city;
    private String name;
    private String logo;

    public Restaurant(JSONObject restaurant) {
        /*
        {
            "id": 1,
            "direction": "Calle 8 # 75-54",
            "city": "El Cerrito",
            "name": "KFC",
            "logo": "/media/logos/FB_IMG_16123283154944612_02gW5CD.jpg",
            "administrator": 3
        }
         */
        try {
            this.id = restaurant.getInt("id");
            this.direction = restaurant.getString("direction");
            this.city = restaurant.getString("city");
            this.name = restaurant.getString("name");
            this.logo = restaurant.getString("logo");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @NonNull
    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", direction='" + direction + '\'' +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
