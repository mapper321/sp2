package com.hebei.zydd.user;

import javax.annotation.Resource;

import com.hebei.core.util.ContextUtil;
import org.springframework.web.bind.annotation.*;
import com.hebei.core.model.PageBean;
import com.hebei.core.model.ResultView;
import com.hebei.core.util.UniqueIdUtil;
import com.hebei.core.util.StringUtil;
import com.hebei.core.web.controller.BaseController; 
import io.swagger.annotations.Api;
import com.hebei.zydd.user.data.VolunteerOrderUser;
import io.swagger.annotations.ApiOperation;
import java.util.Date;

/**
 * 
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-07 18:58:29
 */
@Api(tags="志愿点单报名人员")
@RestController
@RequestMapping("/zydd/user")
public class VolunteerOrderUserController extends BaseController {
	@Resource
	private VolunteerOrderUserService orderUserService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/list")
	public ResultView<VolunteerOrderUser> list(PageBean PageBean,VolunteerOrderUser orderUser) {
		return orderUserService.getAll(PageBean, orderUser);
	}

    @ApiOperation(value = "根据主键查询详情")
	@GetMapping()
    public ResultView<VolunteerOrderUser> get(Long id) {
    	return ResultView.ok(orderUserService.getById(id));
    }

	@ApiOperation(value = "保存/更新")
	@PostMapping()
	public ResultView save(VolunteerOrderUser orderUser) {
		ResultView ResultView=new ResultView();
		orderUser.setUpdateTime(new Date());
		orderUser.setUpdateBy(ContextUtil.getCurrentUserid());
		if(orderUser.getId()==null||orderUser.getId()<=0){
			orderUser.setId(UniqueIdUtil.genId());
			orderUser.setCreateTime(new Date());
			orderUser.setCreateBy(ContextUtil.getCurrentUserid());
			orderUserService.add(orderUser);
		}else{
			orderUserService.update(orderUser);
		}
		ResultView.addKV("id",orderUser.getId().toString());
		return ResultView;
	}

	@ApiOperation(value = "根据主键删除")
    @DeleteMapping()
    public ResultView del(String id) {
        Long[] lAryId = StringUtil.getLongAryByStr(id);
        for (int i = 0; i < lAryId.length; i++) {
            orderUserService.delById(lAryId[i]);
        }
        return new ResultView();
    }
}
