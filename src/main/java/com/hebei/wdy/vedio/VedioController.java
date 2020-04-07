package com.hebei.wdy.vedio;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.hebei.core.model.PageBean;
import com.hebei.core.model.ResultView;
import com.hebei.core.util.UniqueIdUtil;
import com.hebei.core.util.StringUtil;
import com.hebei.core.web.controller.BaseController; 
import io.swagger.annotations.Api;
import com.hebei.wdy.vedio.data.Vedio;
import io.swagger.annotations.ApiOperation;
import java.util.Date;

/**
 * 
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-07 11:02:10
 */
@Api(tags="文明微电影")
@RestController
@RequestMapping("/wdy/vedio")
public class VedioController extends BaseController {
	@Resource
	private VedioService vedioService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/list")
	public ResultView<Vedio> list(PageBean PageBean,Vedio vedio) {
		return vedioService.getAll(PageBean, vedio);
	}

    @ApiOperation(value = "根据主键查询详情")
	@GetMapping()
    public ResultView<Vedio> get(Long id) {
    	return ResultView.ok(vedioService.getById(id));
    }

	@ApiOperation(value = "保存/更新")
	@PostMapping()
	public ResultView save(Vedio vedio) {
		ResultView ResultView=new ResultView();
		vedio.setUpdateTime(new Date());
		if(vedio.getId()==null||vedio.getId()<=0){
			vedio.setId(UniqueIdUtil.genId());
			vedio.setCreateTime(new Date());
			vedioService.add(vedio);
		}else{
			vedioService.update(vedio);
		}
		ResultView.addKV("id",vedio.getId().toString());
		return ResultView;
	}

	@ApiOperation(value = "根据主键删除")
    @DeleteMapping()
    public ResultView del(String id) {
        Long[] lAryId = StringUtil.getLongAryByStr(id);
        for (int i = 0; i < lAryId.length; i++) {
            vedioService.delById(lAryId[i]);
        }
        return new ResultView();
    }
}
