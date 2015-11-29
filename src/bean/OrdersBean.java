package bean;

import java.util.ArrayList;
import java.util.Date;

public class OrdersBean {
    private String orderID;
    private AccountBean client;
    private ArrayList<ProductBean> products;
    private String status;
    private String deliveryAddress;
    private Date pickupTime;
    private boolean cancelled;
    private int totalAmount;

    public OrdersBean() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public AccountBean getClient() {
        return client;
    }

    public void setClient(AccountBean client) {
        this.client = client;
    }

    public ArrayList<ProductBean> getProductBeans() {
        return products;
    }

    public void setProducts(ArrayList<ProductBean> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    public boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalAmount(){
        return totalAmount;
    }
}
