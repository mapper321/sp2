package com.hebei.core.web.interceptor;

import com.hebei.core.util.ContextUtil;
import com.hebei.core.util.JwtTokenUtil;
import com.hebei.core.web.anno.IgnoreAuth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 安全认证拦截器
 *
 * @author: mapper
 * @since: 2020/4/7
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        IgnoreAuth ignoreAuth = null;
        if (handler instanceof HandlerMethod) {
            ignoreAuth = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }
        if (ignoreAuth != null) {
            return true;
        }

        String path = request.getServletPath();
        if ("/error".equals(path)||path.startsWith("/swagger")) {
            return true;
        }
        String token = getToken(request);
        ContextUtil.setCurrentClaims(JwtTokenUtil.parseJWT(token));
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //清理线程变量
        ContextUtil.clearAll();
    }

    private String getToken(HttpServletRequest request) {
        //TODO 服务内部交互token，eg：feign,待完善 ；验证redis等。
        return request.getHeader("token");
    }
}
