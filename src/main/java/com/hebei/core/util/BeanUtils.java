package com.hebei.core.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;


/**
 * BeanUtils的等价类，只是将check exception改为uncheck exception
 *
 * @author mapper
 */
public class BeanUtils {
    private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);
    private static BeanUtilsBean beanUtilsBean = new BeanUtilsBean(new ConvertUtilsBean(), new PropertyUtilsBean());

    /**
     * 可以用于判断 Map,Collection,String,Array,Long是否为空
     *
     * @param o java.lang.Object.
     * @return boolean.
     */
    public static boolean isEmpty(Object o) {
        if (o == null)
            return true;
        if (o instanceof String) {
            if (((String) o).trim().length() == 0) {
                return true;
            }
        } else if (o instanceof Collection) {
            if (((Collection) o).isEmpty()) {
                return true;
            }
        } else if (o.getClass().isArray()) {
            if (((Object[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).isEmpty()) {
                return true;
            }
        } else if (o instanceof Long) {
            if (((Long) o) == null) {
                return true;
            }
        } else if (o instanceof Short) {
            if (((Short) o) == null) {
                return true;
            }
        } else {
            return false;
        }
        return false;

    }


    /**
     * 可以用于判断 Map,Collection,String,Array是否不为空
     *
     * @param o
     * @return
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static boolean isNotEmpty(Long o) {
        return !isEmpty(o);
    }


    /**
     * 判断是否为数字
     *
     * @param o
     * @return
     */
    public static boolean isNumber(Object o) {
        if (o == null)
            return false;
        if (o instanceof Number) {
            return true;
        }
        if (o instanceof String) {
            try {
                Double.parseDouble((String) o);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }


    /**
     * 根据指定的类名判定指定的类是否存在。
     *
     * @param className
     * @return
     */
    public static boolean validClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * 判定类是否继承自父类
     *
     * @param cls         子类
     * @param parentClass 父类
     * @return
     */
    public static boolean isInherit(Class cls, Class parentClass) {
        return parentClass.isAssignableFrom(cls);
    }

    /**
     * 【功能描述】: 将实体类转换为map<br>
     * @param: [o]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: mapper
     * @since: 2020/4/13
     */
    public static Map<String,Object> convertBean2Map(Object o) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return beanUtilsBean.getPropertyUtils().describe(o);
    }


}
