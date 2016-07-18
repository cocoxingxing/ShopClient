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

    }
}
