package com.hebei.wdy.video;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hebei.core.service.BaseService;
import com.hebei.core.mybatis.IEntityDao;
import com.hebei.wdy.video.data.Video;

/**
 *
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-07 15:12:55
 */
@Service
public class VideoService extends BaseService<Video> {
	@Resource
	private VideoDao dao;
	
	public VideoService() {
	}
	
	@Override
	protected IEntityDao<Video, Long> getEntityDao() {
		return dao;
	}
	
}
