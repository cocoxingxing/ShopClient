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
}
