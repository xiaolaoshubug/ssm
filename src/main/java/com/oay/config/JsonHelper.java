package com.oay.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/*********************************************************
 * @Package: com.oay.config
 * @ClassName: JsonHelper.java
 * @Description�� Java�������л�ΪJSON�ַ���
 * -----------------------------------
 * @author��ouay
 * @Version��v1.0
 * @Date: 2020-12-30
 *********************************************************/
public class JsonHelper {
    /**
     * Java�������л�ΪJSON�ַ���
     *
     * @param obj Java����
     * @return json�ַ���
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }
}
