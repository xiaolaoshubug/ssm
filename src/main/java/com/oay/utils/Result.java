package com.oay.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*********************************************************
 * @Package: com.oay.utils
 * @ClassName: Result.java
 * @Description�� ����json
 * -----------------------------------
 * @author��ouay
 * @Version��v1.0
 * @Date: 2020-12-01
 *********************************************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private String success;
    private int code; // 200����������200��ʾ�쳣
    private String msg;
    private Object data;

    public static Result succ(Object data) {
        return succ("true", 200, "�����ɹ�", data);
    }

    public static Result succ(String success, int code, String msg, Object data) {
        Result r = new Result();
        r.setSuccess(success);
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg) {
        return fail("false", 400, msg, null);
    }

    public static Result fail(String success, String msg, Object data) {
        return fail(success, 400, msg, data);
    }

    public static Result fail(String success, int code, String msg, Object data) {
        Result r = new Result();
        r.setSuccess(success);
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
