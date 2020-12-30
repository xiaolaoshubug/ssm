package com.oay.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/*********************************************************
 * @Package: com.oay.config
 * @ClassName: MyKeyGenerator.java
 * @Description������
 * -----------------------------------
 * @author��ouay
 * @Version��v1.0
 * @Date: 2020-12-30
 *********************************************************/
@Component
public class MyKeyGenerator implements KeyGenerator {

    //  û�в�����ѯ����
    private final static String NO_PARAM_KEY = "ALL";
    // keyǰ׺���������ֲ�ͬ��Ŀ�Ļ��棬����ÿ����Ŀ��������
    private String keyPrefix = "ssm";

    @Override
    public Object generate(Object target, Method method, Object... params) {

        char sp = '.';
        StringBuilder strBuilder = new StringBuilder(30);
        strBuilder.append(keyPrefix);
        strBuilder.append(sp);
        // ����
        strBuilder.append(target.getClass().getSimpleName());
        strBuilder.append(sp);
        // ������
        strBuilder.append(method.getName());
        strBuilder.append(sp);
        if (params.length > 0) {
            // ����ֵ
            for (Object object : params) {
                if (BeanHelper.isSimpleValueType(object.getClass())) {
                    strBuilder.append(object);
                } else {
                    strBuilder.append(JsonHelper.toJson(object).hashCode());
                }
            }
        } else {
            strBuilder.append(NO_PARAM_KEY);
        }
        return strBuilder.toString();
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
