package com.hebei.wdy.vedio;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hebei.core.service.BaseService;
import com.hebei.core.mybatis.IEntityDao;
import com.hebei.wdy.vedio.data.Vedio;

/**
 *
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-07 11:02:10
 */
@Service
public class VedioService extends BaseService<Vedio> {
	@Resource
	private VedioDao dao;
	
	public VedioService() {
	}
	
	@Override
	protected IEntityDao<Vedio, Long> getEntityDao() {
		return dao;
	}
	
}
