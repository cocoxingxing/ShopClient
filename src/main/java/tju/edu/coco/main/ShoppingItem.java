package tju.edu.coco.main;

/**
 * Created by Administrator on 2016/7/18.
 */
public class ShoppingItem {

    private String name;
    private double price;
    private int num;
    private String unit;
    private double discountPrice = 0.0;
    private String discountTag;
    private double totalPrice = 0.0;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return this.price;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public int getNum() {
        return this.num;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getUnit() {
        return this.unit;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
    public double getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountTag(String discountTag) {
        this.discountTag = discountTag;
    }
    public String getDiscountTag() {
        return this.discountTag;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public double getTotalPrice() {
        return this.totalPrice;
    }
}
