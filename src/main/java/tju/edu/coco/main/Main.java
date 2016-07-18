/**
 * Created by Administrator on 2016/7/18.
 */

package tju.edu.coco.main;

import java.util.Hashtable;

import tju.edu.coco.consts.Consts;
import tju.edu.coco.utils.FileHelper;

public class Main {
    public static ShopClient shopClient = new ShopClient();
    public static void main(String[] args) {
        Hashtable<String, Integer> shoppingItems = FileHelper.readFileToItems(Consts.ITEM_FILE_PATH);
        shopClient.setShoppingItems(shoppingItems);
        shopClient.settlement();
        shopClient.printShoppingList();
    }
}
