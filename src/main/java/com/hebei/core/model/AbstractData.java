package com.hebei.core.model;


import com.hebei.core.model.constant.RespStatus;

public abstract class AbstractData {
    private boolean isSucceed;// 是否执行成功
    private String message;// 消息
    private int code;    //状态码

    public AbstractData() {
        isSucceed = true;
        code = RespStatus.OK.getCode();
        message = "";
    }


    public boolean isSucceed() {
        return isSucceed;
    }


    public void setSucceed(boolean isSucceed) {
        this.isSucceed = isSucceed;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
