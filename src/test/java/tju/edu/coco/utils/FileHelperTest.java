package tju.edu.coco.utils;

import java.util.Set;

import org.junit.Test;
import tju.edu.coco.consts.Consts;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/17.
 */
public class FileHelperTest {
    @Test
    public void should_return_size_3_when_read_from_items_file() throws Exception {
        String filePath = Consts.ITEM_FILE_PATH;
        assertEquals(3, FileHelper.readFileToItems(filePath).size());
    }

    @Test
    public void should_return_size_2_when_parse_discount_file() {
        Set<String> discounts = FileHelper.getDiscountItemsByType("3for2");
        assertEquals(2, discounts.size());
    }
    
    @Test
    public void should_return_3for2_when_get_preference_from_discount_file() {
        String tag = "preference";
        assertEquals("3for2", FileHelper.getDiscountPreference(tag));
    }
}