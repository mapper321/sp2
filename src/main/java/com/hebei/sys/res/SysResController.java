package com.hebei.sys.res;

import com.hebei.core.model.PageBean;
import com.hebei.core.model.ResultView;
import com.hebei.core.util.StringUtil;
import com.hebei.core.util.UniqueIdUtil;
import com.hebei.core.web.controller.BaseController;
import com.hebei.sys.res.data.SysRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-14 09:47:55
 */
@Api(tags = "菜单资源")
@RestController
@RequestMapping("/sys/res")
public class SysResController extends BaseController {
    @Resource
    private SysResService sysResService;

    @ApiOperation(value = "列表查询")
    @GetMapping("/list")
    public ResultView<SysRes> list(PageBean pageBean, SysRes sysRes) {
        return sysResService.getAll(pageBean, sysRes);
    }

    @ApiOperation(value = "根据主键查询详情")
    @GetMapping()
    public ResultView<SysRes> get(Long id) {
        return ResultView.ok(sysResService.getById(id));
    }

    @ApiOperation(value = "保存/更新")
    @PostMapping()
    public ResultView save(SysRes sysRes) {
        ResultView ResultView = new ResultView();
        sysRes.setUpdateTime(new Date());
        if (sysRes.getId() == null || sysRes.getId() <= 0) {
            sysRes.setId(UniqueIdUtil.genId());
            sysRes.setCreateTime(new Date());
            if (sysRes.getPid() != null) {
                if (sysRes.getPid() > 0) {
                    sysRes.setPath(sysResService.getById(sysRes.getPid()).getPath() + "." + sysRes.getId());
                } else {
                    sysRes.setPath(0 + "." + sysRes.getId());
                }
            }
            sysResService.add(sysRes);
        } else {
            sysResService.update(sysRes);
        }
        ResultView.addKV("id", sysRes.getId().toString());
        return ResultView;
    }


    @ApiOperation(value = "根据主键删除")
    @DeleteMapping()
    public ResultView del(String id) {
        Long[] lAryId = StringUtil.getLongAryByStr(id);
        for (int i = 0; i < lAryId.length; i++) {
            sysResService.delById(lAryId[i]);
        }
        return new ResultView();
    }

    @ApiOperation(value = "根据菜单别名查询其下一级子菜单")
    @GetMapping("/getChildrenByAlias")
    public ResultView<SysRes> getChildrenByAlias(String alias) {
        return ResultView.ok(sysResService.getChildrenByAlias(alias));
    }

}
