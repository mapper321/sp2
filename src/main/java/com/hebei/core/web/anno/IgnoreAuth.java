package com.hebei.core.web.anno;

import java.lang.annotation.*;

/**
 * 免登陆注解
 * @author: mapper
 * @since: 2020/4/7
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface  IgnoreAuth {
}
