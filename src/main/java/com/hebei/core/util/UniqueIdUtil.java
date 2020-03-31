package com.hebei.core.util;

import com.hebei.core.util.unique.SnowflakeIdWorker;

import java.util.UUID;
/**
 * id生成
 * @author: mapper
 * @since: 2020/3/31
 */
public class UniqueIdUtil {

    public static long genId() {
        return SnowflakeIdWorker.generateId();
    }

    public static String genUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
