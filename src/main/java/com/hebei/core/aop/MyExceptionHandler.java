package com.hebei.core.aop;

import com.hebei.core.model.ResultView;
import com.hebei.core.web.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 异常处理器
 */
@RestControllerAdvice
public class MyExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 自定义异常
     */
    @ExceptionHandler(MyException.class)
    public ResultView handleMyException(MyException e) {
        logger.error(e.getMessage(), e);
        return ResultView.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResultView handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return ResultView.error("数据库中已存在该记录");
    }
    

    @ExceptionHandler(Exception.class)
    public ResultView handleException(Exception e) {
        logger.error(e.getMessage(), e);
        //其他类型错误，暂时不返回前端，统一展示：“内部错误”
        return ResultView.error();
    }
    
}
