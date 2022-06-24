package dev.khor.customfood.models;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private int id;
    private String email;
    private String type;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private String birthday;
    private String direction;
    private String city;
    private Restaurant restaurant;
    private String token;

    public User(JSONObject response) {
        try{
            this.id =  response.getInt("id");
            this.email =  response.getString("email");
            this.type = response.getString("type");
            this.firstName = response.getString("first_name");
            this.lastName = response.getString("last_name");
            this.cellPhone = response.getString("cell_phone");
            this.birthday = response.getString("birth_day");
            this.direction = response.getString("direction");
            this.city = response.getString("city");
            this.token = response.getString("token");
            if(!response.isNull("restaurant")){
                this.restaurant = new Restaurant(response.getJSONObject("restaurant"));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", direction='" + direction + '\'' +
                ", restaurant='" + restaurant + '\'' +
                ", city='" + city + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
