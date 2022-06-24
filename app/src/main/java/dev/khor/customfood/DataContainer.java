package dev.khor.customfood;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import dev.khor.customfood.models.Ingredient;
import dev.khor.customfood.models.Product;
import dev.khor.customfood.models.User;

public class DataContainer {
    public static User user;
    public static ArrayList<Product> products= new ArrayList<Product>();;
    public static ArrayList<Product> orderProducts= new ArrayList<Product>();;

    public static FragmentManager fm;
    public static int total=0;
}
