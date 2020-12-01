package com.oay.utils;

import java.util.UUID;

/*********************************************************
 * @Package: com.oay.utils
 * @ClassName: IDUtil.java
 * @Description£º  Éú³ÉUUID
 * -----------------------------------
 * @author£ºouay
 * @Version£ºv1.0
 * @Date: 2020-11-25
 *********************************************************/
public class IDUtil {

    public static String genId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
