package com.hebei.core.util;

import com.alibaba.ttl.TransmittableThreadLocal;
import io.jsonwebtoken.Claims;

/**
 * 当前线程变量工具类
 *
 * @author: mapper
 * @since: 2020/4/7
 */
public class ContextUtil {
    private static ThreadLocal<Long> curUserid = new TransmittableThreadLocal();
    private static ThreadLocal<Claims> curClaims = new TransmittableThreadLocal();
    private static ThreadLocal<String> curIp = new TransmittableThreadLocal();
    //内部交互用的clientid
    private static final String INTERNAL_CLIENT = "internal";

    /**
     * 获取当前登陆用户id
     *
     * @return
     */
    public static Long getCurrentUserid() {
        Long userid = null;
        if((userid = curUserid.get())==null){
            Claims claims = curClaims.get();
            if(claims != null){
                userid = Long.parseLong(claims.get("userId")+"");
                curUserid.set(userid);
            }
        }
        return userid;
    }

    public static void setCurrentUserid(Long userid) {
        curUserid.set(userid);
    }

    /**
     * 获取当前token解密后的信息
     *
     * @return
     */
    public static Claims getCurrentCliams() {
        return curClaims.get();
    }

    public static void setCurrentClaims(Claims claims) {
        curClaims.set(claims);
    }

    /**
     * 获取登陆的客户端信息，如：	pc|app
     *
     * @return
     */
    public static String getCurrClientId() {
        Claims claims = curClaims.get();
        if (claims != null) {
            return (String) claims.get("clientId");
        }
        return INTERNAL_CLIENT;
    }

    public static void setCurrentIp(String ip) {
        curIp.set(ip);
    }

    public static String getCurrentIp() {
        return curIp.get();
    }

    public static void clearAll() {
        curUserid.remove();
        curClaims.remove();
        curIp.remove();
    }
}
