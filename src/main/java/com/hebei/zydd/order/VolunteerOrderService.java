package com.hebei.zydd.order;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hebei.core.service.BaseService;
import com.hebei.core.mybatis.IEntityDao;
import com.hebei.zydd.order.data.VolunteerOrder;

/**
 *
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-03-31 21:35:16
 */
@Service
public class VolunteerOrderService extends BaseService<VolunteerOrder> {
	@Resource
	private VolunteerOrderDao dao;
	
	public VolunteerOrderService() {
	}
	
	@Override
	protected IEntityDao<VolunteerOrder, Long> getEntityDao() {
		return dao;
	}
	
}
