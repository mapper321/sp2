package com.hebei.wdy.vedio;

import org.springframework.stereotype.Repository;
import com.hebei.core.mybatis.BaseDao;
import com.hebei.wdy.vedio.data.Vedio;
/**
 *
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-07 11:02:10
 */
@Repository
public class VedioDao extends BaseDao<Vedio> {
	@Override
	public Class<Vedio> getEntityClass()
	{
		return Vedio.class;
	}

}