package com.hebei.wdy.video;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.hebei.core.model.PageBean;
import com.hebei.core.model.ResultView;
import com.hebei.core.util.UniqueIdUtil;
import com.hebei.core.util.StringUtil;
import com.hebei.core.web.controller.BaseController; 
import io.swagger.annotations.Api;
import com.hebei.wdy.video.data.Video;
import io.swagger.annotations.ApiOperation;
import java.util.Date;

/**
 * 
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-07 15:12:55
 */
@Api(tags="文明微电影")
@RestController
@RequestMapping("/wdy/video")
public class VideoController extends BaseController {
	@Resource
	private VideoService videoService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/list")
	public ResultView<Video> list(PageBean pageBean,Video video) {
		return videoService.getAll(pageBean, video);
	}

    @ApiOperation(value = "根据主键查询详情")
	@GetMapping()
    public ResultView<Video> get(Long id) {
    	return ResultView.ok(videoService.getById(id));
    }

	@ApiOperation(value = "保存/更新")
	@PostMapping()
	public ResultView save(Video video) {
		ResultView ResultView=new ResultView();
		video.setUpdateTime(new Date());
		if(video.getId()==null||video.getId()<=0){
			video.setId(UniqueIdUtil.genId());
			video.setCreateTime(new Date());
			videoService.add(video);
		}else{
			videoService.update(video);
		}
		ResultView.addKV("id",video.getId().toString());
		return ResultView;
	}

	@ApiOperation(value = "根据主键删除")
    @DeleteMapping()
    public ResultView del(String id) {
        Long[] lAryId = StringUtil.getLongAryByStr(id);
        for (int i = 0; i < lAryId.length; i++) {
            videoService.delById(lAryId[i]);
        }
        return new ResultView();
    }
}
