package com.hebei.zydd.user;

import org.springframework.stereotype.Repository;
import com.hebei.core.mybatis.BaseDao;
import com.hebei.zydd.user.data.VolunteerOrderUser;
/**
 *
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-07 18:58:29
 */
@Repository
public class VolunteerOrderUserDao extends BaseDao<VolunteerOrderUser> {
	@Override
	public Class<VolunteerOrderUser> getEntityClass()
	{
		return VolunteerOrderUser.class;
	}

}