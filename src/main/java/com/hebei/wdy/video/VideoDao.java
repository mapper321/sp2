package com.hebei.wdy.video;

import org.springframework.stereotype.Repository;
import com.hebei.core.mybatis.BaseDao;
import com.hebei.wdy.video.data.Video;
/**
 *
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-07 15:12:55
 */
@Repository
public class VideoDao extends BaseDao<Video> {
	@Override
	public Class<Video> getEntityClass()
	{
		return Video.class;
	}

}