package com.hebei.core.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Before("com.hebei.core.util.PointCutUtil.allWebRequest()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Object[] args = joinPoint.getArgs();
        String[] parmArr = methodSignature.getParameterNames();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < parmArr.length; i++) {
            if (args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse
                    || args[i] instanceof ServletRequest || args[i] instanceof ServletResponse
            ) {
                continue;
            }
            String value;
            try {
                if (args[i] instanceof MultipartFile) {
                    value = ((MultipartFile) args[i]).getOriginalFilename();
                } else if (args[i] instanceof File) {
                    value = ((File) args[i]).getName();
                } else {
                    value = JSONObject.toJSONString(args[i]);
                }
            } catch (Exception e) {
                continue;
            }
            sb.append(",").append(parmArr[i]).append(":").append(value);
        }
        // 记录下请求内容
        log.info("============================ : start : ============================");
        log.info("CLASS_METHOD : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("URL : {}", request.getRequestURL().toString());
        log.info("HTTP_METHOD : {}", request.getMethod());
        //nginx中添加如下配置效果最优
        //proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        log.info("IP : {}", ip.replaceAll(",", " ==>"));
        log.info("ARGS : [{}]", (sb.length() > 0 ? sb.substring(1) : ""));
    }

    @AfterReturning(returning = "ret", pointcut = "com.hebei.core.util.PointCutUtil.allWebRequest()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("RESPONSE : {}", ret);
        log.info("========================== : end : =================================time:{}", (System.currentTimeMillis() - startTime.get()));
    }
}
