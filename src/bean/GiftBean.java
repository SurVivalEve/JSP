package bean;

public class GiftBean {
    private String id;
    private String name;
    private String picturePath;
    private int requireBonus;

    public GiftBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getRequireBonus() {
        return requireBonus;
    }

    public void setRequireBonus(int requireBonus) {
        this.requireBonus = requireBonus;
    }
}
