package tju.edu.coco.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tju.edu.coco.consts.Consts;

/**
 * Created by Administrator on 2016/7/17.
 */
public class FileHelper {

    /**
     * read and return the content of the file
     * @param path
     * @return
     */
    public static String readFile(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        String content = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            // read the file by line
            while ((line = reader.readLine()) != null) {
                content = content + line;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }

    /**
     * read from the file, and return the shop items by hash table
     * @param path
     * @return
     */
    public static Hashtable<String, Integer> readFileToItems(String path) {
        Hashtable<String, Integer> values = new Hashtable<String, Integer>();
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            // read the file by lines
            while ((line = reader.readLine()) != null) {
                // ignore the start and end line
                if((!line.equals("[")) && (!line.equals("]"))) {
                    line = line.substring(line.indexOf("'") + 1, line.indexOf("'", line.indexOf("'") + 1));
                    // check the num of items
                    if(line.indexOf("-") != -1) {
                        String code = line.substring(0, line.indexOf("-"));
                        int num = Integer.parseInt(line.substring(line.indexOf("-") + 1, line.length()));
                        if(values.containsKey(code)) {
                            int tempNum = values.get(code);
                            values.put(code, tempNum + num);
                        } else {
                            values.put(code, num);
                        }
                    } else {
                        if(values.containsKey(line)) {
                            int tempNum = values.get(line);
                            values.put(line, tempNum + 1);
                        } else {
                            values.put(line, 1);
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return values;
    }

    /**
     * get discount items from discount config file
     * @param type
     * @return
     */
    public static Set<String> getDiscountItemsByType(String type) {
        String text = readFile(Consts.DISCOUNT_FILE_PATH);
        JSONObject jsonObject = JSON.parseObject(text);

        Set<String> result = new HashSet<String>();
        JSONArray jsonArray = JSON.parseArray(jsonObject.get(type).toString());
        for(int i = 0; i < jsonArray.size(); ++i) {
            result.add(jsonArray.get(i).toString());
        }
        return result;
    }

    /**
     * get discount preference by discount config file
     * @return
     */
    public static String getDiscountPreference(String tag) {
        String text = readFile(Consts.DISCOUNT_FILE_PATH);
        JSONObject jsonObject = JSON.parseObject(text);
        return jsonObject.get(tag).toString();
    }
}
