package com.hebei.sys.res;

import com.hebei.core.mybatis.BaseDao;
import com.hebei.sys.res.data.SysRes;
import org.springframework.stereotype.Repository;

/**
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-14 09:47:55
 */
@Repository
public class SysResDao extends BaseDao<SysRes> {
    @Override
    public Class<SysRes> getEntityClass() {
        return SysRes.class;
    }

}