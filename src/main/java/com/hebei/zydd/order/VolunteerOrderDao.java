package com.hebei.zydd.order;

import org.springframework.stereotype.Repository;
import com.hebei.core.mybatis.BaseDao;
import com.hebei.zydd.order.data.VolunteerOrder;
/**
 *
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-03-31 21:35:16
 */
@Repository
public class VolunteerOrderDao extends BaseDao<VolunteerOrder> {
	@Override
	public Class<VolunteerOrder> getEntityClass()
	{
		return VolunteerOrder.class;
	}

}