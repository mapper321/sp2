package com.hebei.core.web.exception;

import com.hebei.core.model.constant.RespStatus;
import lombok.Data;

/**
 * @author: mapper
 * @since: 2020/4/7
 */
@Data
public class MyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code = RespStatus.ERROR.getCode();

    public MyException(String msg) {
        super(msg);
    }

    public MyException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public MyException(String msg, Throwable e) {
        super(msg, e);
    }

    public MyException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
    }

    @Override
    public String toString(){
        return code+","+getMessage();
    }
}
