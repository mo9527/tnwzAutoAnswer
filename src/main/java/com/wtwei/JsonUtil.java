package com.wtwei;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.json.JSONUtil;


public class JsonUtil {
    public static String serialize(Object jsonObj) {
        JsonConfig jsonConfig = new JsonConfig();

        JSONObject jo = JSONObject.fromObject(jsonObj, jsonConfig);

        return jo.toString();
    }

    public static Object deserialize(String jsonStr) {
        Object jsonObj = new Object();
        if (StringUtil.isNotEmpty(new Object[]{jsonStr})) {
            try {
                jsonObj = JSONUtil.deserialize(jsonStr);
            } catch (Exception var3) {
                throw new RuntimeException(var3);
            }
        }
        return jsonObj;
    }
}
