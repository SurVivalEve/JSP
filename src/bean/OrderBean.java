package bean;

/**
 * Created by Sur.Vival on 23/11/2015.
 */
public class OrderBean {
    private String id;
    private String status;
    private String delivery;
    private String Clientid;

    public OrderBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getClientid() {
        return Clientid;
    }

    public void setClientid(String clientid) {
        Clientid = clientid;
    }
}
