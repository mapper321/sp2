package com.hebei.core.util;

import com.hebei.core.model.constant.RespStatus;
import com.hebei.core.web.exception.MyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtTokenUtil {
    //token 12小时后过期
    public final static Long EXPIRE = 3600 * 12 * 1000L;
    //密钥
    private final static String SECRET = "hebei";
    //签发者
    private final static String ISSUER = "hebei";





    /**
     * 通过用户id，客户端id 创建token
     *
     * @param userId
     * @param userId
     * @return
     */
    public static String createJWT(String userId, String clientId) {
        //获取当前时间
        long nowMillis = System.currentTimeMillis();
        //将token有效时间提前十分钟
        long effMillis = nowMillis - 600000;
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder()
                .claim("userId", userId)
                .claim("clientId", clientId)
                .signWith(SignatureAlgorithm.HS256, getKeyInstance(SECRET));
        //添加Token过期时间
        Date now = new Date(effMillis);
        long expMillis = nowMillis + EXPIRE;
        Date exp = new Date(expMillis);
        builder
                .setExpiration(exp)
                .setNotBefore(now)
                .setIssuer(ISSUER);
        //生成JWT
        return builder.compact();
    }


    /**
     * 获取token中的用户id
     */
    public static String getUserId(String token) {
        Claims claims = parseJWT(token);
        String userId = null;
        if (claims != null) {
            userId = (String) claims.get("userId");
        }
        return userId;
    }

    /**
     * 获取token中的clientId
     */
    public static String getClientId(String token) {
        Claims claims = parseJWT(token);
        String clientId = null;
        if (claims != null) {
            clientId = (String) claims.get("clientId");
        }
        return clientId;
    }

    /**
     * 解析token，获取其中附带的数据
     *
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getKeyInstance(SECRET))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            if (ex.getMessage().contains("expired")) {
                throw new MyException(RespStatus.AUTH_EXPIRED.getCode(), RespStatus.AUTH_EXPIRED.getMsg(),ex);
            } else {
                throw new MyException(RespStatus.UNAUTHORIZED.getCode(), "无效认证",ex);
            }
        }
    }

    private static Key getKeyInstance(String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    public static void main(String[] args) {
        String createJWT = createJWT("1", "pc");
//        String s = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwidXNlcklkIjoiMSIsImNsaWVudElkIjoiMSIsImV4cCI6MTUyNzM2MTM0OSwibmJmIjoxNTI3MzE3NTQ5LCJpc3MiOiJ3d3cuYmt2aXRvLmNvbSJ9.tUcgVMwO48z6hf0dcBjg4YyPIYy9rMl8nG4CpxlWoFA1";
        System.out.println(createJWT);
        Claims parseJWT = parseJWT(createJWT);
        System.out.println(parseJWT);
    }

}
