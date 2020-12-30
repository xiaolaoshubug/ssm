package com.oay.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/*********************************************************
 * @Package: com.oay.config
 * @ClassName: JsonHelper.java
 * @Description： Java对象序列化为JSON字符串
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2020-12-30
 *********************************************************/
public class JsonHelper {
    /**
     * Java对象序列化为JSON字符串
     *
     * @param obj Java对象
     * @return json字符串
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }
}
