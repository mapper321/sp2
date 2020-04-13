package com.hebei.zydd.order;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.hebei.core.model.PageBean;
import com.hebei.core.model.ResultView;
import com.hebei.core.util.UniqueIdUtil;
import com.hebei.core.util.StringUtil;
import com.hebei.core.web.controller.BaseController; 
import io.swagger.annotations.Api;
import com.hebei.zydd.order.data.VolunteerOrder;
import io.swagger.annotations.ApiOperation;
import java.util.Date;

/**
 * 
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-13 10:06:00
 */
@Api(tags="志愿点单")
@RestController
@RequestMapping("/zydd/order")
public class VolunteerOrderController extends BaseController {
	@Resource
	private VolunteerOrderService orderService;

	@ApiOperation(value = "列表查询")
	@GetMapping("/list")
	public ResultView<VolunteerOrder> list(PageBean PageBean,VolunteerOrder order) {
		return orderService.getAll(PageBean, order);
	}

    @ApiOperation(value = "根据主键查询详情")
	@GetMapping()
    public ResultView<VolunteerOrder> get(Long id) {
    	return ResultView.ok(orderService.getById(id));
    }

	@ApiOperation(value = "保存/更新")
	@PostMapping()
	public ResultView save(VolunteerOrder order) {
		ResultView ResultView=new ResultView();
		order.setUpdateTime(new Date());
		if(order.getId()==null||order.getId()<=0){
			order.setId(UniqueIdUtil.genId());
			order.setCreateTime(new Date());
			orderService.add(order);
		}else{
			orderService.update(order);
		}
		ResultView.addKV("id",order.getId().toString());
		return ResultView;
	}

	@ApiOperation(value = "根据主键删除")
    @DeleteMapping()
    public ResultView del(String id) {
        Long[] lAryId = StringUtil.getLongAryByStr(id);
        for (int i = 0; i < lAryId.length; i++) {
            orderService.delById(lAryId[i]);
        }
        return new ResultView();
    }
}
