package com.hebei.core.model.constant;

import lombok.Data;
import org.omg.CORBA.NO_PERMISSION;

/**
 * @author: mapper
 * @since: 2020/4/7
 */
public enum RespStatus {

    OK(200,""),
    ERROR(500,"服务内部出错了~"),
    UNAUTHORIZED(1001,"无效认证"),
    AUTH_EXPIRED(1003,"认证过期"),
    NO_PERMISSION(1002,"无权限");

    private int code;
    private String msg;

    private RespStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
}
