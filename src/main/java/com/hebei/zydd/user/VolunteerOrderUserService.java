package com.hebei.zydd.user;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hebei.core.service.BaseService;
import com.hebei.core.mybatis.IEntityDao;
import com.hebei.zydd.user.data.VolunteerOrderUser;

/**
 *
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-07 18:58:29
 */
@Service
public class VolunteerOrderUserService extends BaseService<VolunteerOrderUser> {
	@Resource
	private VolunteerOrderUserDao dao;
	
	public VolunteerOrderUserService() {
	}
	
	@Override
	protected IEntityDao<VolunteerOrderUser, Long> getEntityDao() {
		return dao;
	}
	
}
