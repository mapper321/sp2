package com.hebei.core.util;

import org.aspectj.lang.annotation.Pointcut;

/**
 * web请求切面
 * @author: mapper
 * @since: 2020/3/31
 */
public class PointCutUtil {
    /**
     * 【功能描述】: web请求切面<br>
     * @param: []
     * @return: void
     * @author: mapper
     * @since: 2020/3/31
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + " || @annotation(org.springframework.web.bind.annotation.PostMapping)"
            + " || @annotation(org.springframework.web.bind.annotation.DeleteMapping)"
            + " || @annotation(org.springframework.web.bind.annotation.GetMapping)"
            + " || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void allWebRequest() {}

}
