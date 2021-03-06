package com.hebei.core.model;

import com.hebei.core.model.constant.RespStatus;

import java.util.List;

public class ResultView<T> extends GenericResultView<T> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static ResultView ok() {
        return ok("");
    }

    public static ResultView ok(String message) {
        return ok(RespStatus.OK.getCode(), message);
    }

    public static ResultView ok(int code, String message) {
        ResultView rv = new ResultView();
        rv.setMessage(message);
        rv.setCode(code);
        return rv;
    }

    public static ResultView ok(Object obj) {
        ResultView rv = new ResultView();
        rv.appendObject(obj);
        rv.setTotal((long) rv.getRows().size());
        return rv;
    }

    public static ResultView ok(List list) {
        ResultView rv = new ResultView();
        rv.appendRows(list);
        rv.setTotal((long) list.size());
        return rv;
    }


    public static ResultView error() {
        return error(RespStatus.ERROR.getMsg());
    }

    public static ResultView error(String message) {
        return error(RespStatus.ERROR.getCode(), message);
    }

    public static ResultView error(int code, String message) {
        ResultView rv = new ResultView();
        rv.setSucceed(false);
        rv.setCode(code);
        rv.setMessage(message);
        return rv;
    }
}
