package bean;

import java.util.Date;

/**
 * Created by Sur.Vival on 23/11/2015.
 */
public class OrdersBean {
    private String orderID;
    private AccountBean client;
    private String status;
    private String deliveryAddress;
    private Date pickupTime;
    private String cancelled;

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

    public String getCancelled() {
        return cancelled;
    }

    public void setCancelled(String cancelled) {
        this.cancelled = cancelled;
    }
}