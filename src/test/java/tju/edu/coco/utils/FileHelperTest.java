package tju.edu.coco.utils;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/17.
 */
public class FileHelperTest {
    @Test
    public void readFileToItems() throws Exception {
        String filePath = "././data/items.txt";
        assertEquals(3, FileHelper.readFileToItems(filePath).size());
        assertEquals(new Integer(3), FileHelper.readFileToItems(filePath).get("ITEM000005"));
        assertEquals(new Integer(2), FileHelper.readFileToItems(filePath).get("ITEM000003"));
    }
}