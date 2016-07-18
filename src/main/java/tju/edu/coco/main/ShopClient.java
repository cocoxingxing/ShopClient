/**
 * Created by Administrator on 2016/7/18.
 */

package tju.edu.coco.main;

import java.util.*;
import java.util.function.ToLongBiFunction;

import tju.edu.coco.consts.Consts;
import tju.edu.coco.utils.FileHelper;
import tju.edu.coco.utils.Tools;

public class ShopClient {
    private Set<String> discountThreeForTwo = new HashSet<String>();
    private Set<String> discount95 = new HashSet<String>();
    private Hashtable<String, Item> dbItems = new Hashtable<String, Item>();
    private Hashtable<String, Integer> shoppingItems = new Hashtable<String, Integer>();
    private Set<ShoppingItem> finalShoppingItems = new LinkedHashSet<ShoppingItem>();

    public ShopClient() {
        loadConfigInfo();
        loadDBItems();
    }

    /**
     * load the discount items info from discount config file
     */
    public void loadConfigInfo() {
        discountThreeForTwo = FileHelper.getDiscountItemsByType(Consts.DISCOUNT_3_FOR_2_TAG);
        discount95 = FileHelper.getDiscountItemsByType(Consts.DISCOUNT_95_TAG);
    }

    /**
     * load all the items info from db file
     */
    public void loadDBItems() {
        dbItems = FileHelper.getDBItems(Consts.DB_FILE_PATH);
    }

    /**
     * set the shopping itrms
     * @param shoppingItems
     */
    public void setShoppingItems(Hashtable<String, Integer> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    /**
     * settlement for the shopping items
     */
    public void settlement() {
        Enumeration<String> keys = this.shoppingItems.keys();
        while(keys.hasMoreElements()) {
            String key = keys.nextElement();
            ShoppingItem item = discountItem(key, this.shoppingItems.get(key));
            if(item != null) {
                finalShoppingItems.add(item);
            }
        }
    }

    /**
     * find the item discount info and count the final ShoppingItem
     * @param code
     * @param num
     * @return
     */
    public ShoppingItem discountItem(String code, int num) {
        ShoppingItem shoppingItem = new ShoppingItem();
        Enumeration<String> keys = this.dbItems.keys();

        Item item = null;
        if(dbItems.containsKey(code)) {
            item = dbItems.get(code);
        } else {
            return null;
        }
        shoppingItem.setName(item.getName());
        shoppingItem.setPrice(item.getPrice());
        shoppingItem.setNum(num);
        shoppingItem.setTotalPrice(Tools.formatDouble(num*item.getPrice()));
        String tag = "";
        if(discountThreeForTwo.contains(code) && num >= 3) {
            tag = "3for2";
            int discountNum = Math.floorDiv(num, 3);
            shoppingItem.setDiscountPrice(Tools.formatDouble(item.getPrice() * discountNum));
        }
        if(tag.equals("") && discount95.contains(code)) {
            tag = "95%";
            shoppingItem.setDiscountPrice(Tools.formatDouble(item.getPrice() * num * 0.05));
        }
        if(tag.equals("")) {
            shoppingItem.setDiscountPrice(0.00);
        }
        shoppingItem.setTotalPrice(Tools.formatDouble(shoppingItem.getTotalPrice() - shoppingItem.getDiscountPrice()));
        shoppingItem.setDiscountTag(tag);
        return shoppingItem;
    }

    /**
     * print the shopping list
     */
    public void printShoppingList() {
        String normalItemsText = "";
        String discount3For2ItemsText = "";
        String discount95ItemsText = "";
        double totalPrice = 0.0;
        double totalDiscountPrice = 0.0;

        for(ShoppingItem item : finalShoppingItems) {
            totalPrice += item.getTotalPrice();
            if(item.getDiscountTag().equals(Consts.DISCOUNT_3_FOR_2_TAG)) {
                discount3For2ItemsText += "名称：" + item.getName() + "，数量：" + item.getNum() +"瓶" + System.getProperty("line.separator");
                totalDiscountPrice += item.getDiscountPrice();
            } else if(item.getDiscountTag().equals(Consts.DISCOUNT_95_TAG)) {
                discount95ItemsText += "名称：" + item.getName() + "，数量：" + item.getNum() + "，单价：" + Tools.formatDoubleToStr(item.getPrice()) + "(元)，小计：" + Tools.formatDoubleToStr(item.getTotalPrice()) + "，节省" + Tools.formatDoubleToStr(item.getDiscountPrice()) + "(元)" + System.getProperty("line.separator");
                totalDiscountPrice += item.getDiscountPrice();
            } else {
                normalItemsText += "名称：" + item.getName() + "，数量：" + item.getNum() + "，单价：" + Tools.formatDoubleToStr(item.getPrice()) + "(元)，小计：" + Tools.formatDoubleToStr(item.getTotalPrice()) + System.getProperty("line.separator");
            }
        }
        System.out.println("```");
        System.out.println("***<没钱赚商店>购物清单***");
        System.out.print(normalItemsText + discount95ItemsText);
        System.out.println("----------------------");
        if(!discount3For2ItemsText.equals("")) {
            System.out.println("买二赠一商品：");
            System.out.print(discount3For2ItemsText);
            System.out.println("----------------------");
        }
        System.out.println("总计："+ Tools.formatDoubleToStr(totalPrice) + "(元)");
        System.out.println("节省："+ Tools.formatDoubleToStr(totalDiscountPrice) + "(元)");
        System.out.println("**********************");
        System.out.println("```");
    }
}
