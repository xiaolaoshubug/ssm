package com.oay.config;

import org.springframework.util.ClassUtils;

import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

/*********************************************************
 * @Package: com.oay.config
 * @ClassName: BeanHelper.java
 * @Description�� �ж��Ƿ��Ǽ�ֵ����
 * -----------------------------------
 * @author��ouay
 * @Version��v1.0
 * @Date: 2020-12-30
 *********************************************************/
public class BeanHelper {
    /**
     * �ж��Ƿ��Ǽ�ֵ����.�����������������͡�CharSequence��Number��Date��URL��URI��Locale��Class;
     *
     * @param clazz
     * @return
     */
    public static boolean isSimpleValueType(Class<?> clazz) {
        return (ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.isEnum() || CharSequence.class.isAssignableFrom(clazz)
                || Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz) || URI.class == clazz
                || URL.class == clazz || Locale.class == clazz || Class.class == clazz);
    }
}
