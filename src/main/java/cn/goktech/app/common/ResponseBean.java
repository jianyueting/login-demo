package cn.goktech.app.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jian Yueting
 */
@Data
public class ResponseBean implements Serializable {
    private static final int OK_CODE = 0;
    private static final int ERROR_CODE = 1;

    private int code;
    private String msg;
    private long timestamp;

    public ResponseBean() {
        this.timestamp = System.currentTimeMillis();
    }

    public static ResponseBean OkBean() {
        ResponseBean bean = new ResponseBean();
        bean.code = OK_CODE;
        return bean;
    }

    public static ResponseBean OkBean(String msg) {
        ResponseBean bean = new ResponseBean();
        bean.code = OK_CODE;
        bean.msg = msg;
        return bean;
    }

    public static ResponseBean ErrorBean() {
        ResponseBean bean = new ResponseBean();
        bean.code = ERROR_CODE;
        return bean;
    }

    public static ResponseBean ErrorBean(String msg) {
        ResponseBean bean = new ResponseBean();
        bean.code = ERROR_CODE;
        bean.msg = msg;
        return bean;
    }
}
