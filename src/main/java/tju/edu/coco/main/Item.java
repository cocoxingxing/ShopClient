/**
 * Created by Administrator on 2016/7/18.
 */

package tju.edu.coco.main;

public class Item {
    private String name; // shop item name
    private double price; // shop item price(yuan)
    private String code; // shop item identification code
    private String unit; // shop item unit

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

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return this.code;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getUnit() {
        return this.unit;
    }
}
