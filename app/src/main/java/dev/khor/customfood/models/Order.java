package dev.khor.customfood.models;

public class Order {
    private String idOrder, status;
    private int totalOrder;

    public Order(String idOrder,String status,int price){
        this.idOrder= idOrder;
        this.status=status;
        this.totalOrder=price;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public int getTotalOrders() {
        return totalOrder;
    }

    public String getStatus() {
        return status;
    }
}
