package com.hebei.sys.res;

import com.hebei.core.mybatis.IEntityDao;
import com.hebei.core.service.BaseService;
import com.hebei.core.util.BeanUtils;
import com.hebei.core.util.StringUtil;
import com.hebei.sys.res.data.SysRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-14 09:47:55
 */
@Service
@Slf4j
public class SysResService extends BaseService<SysRes> {
    @Resource
    private SysResDao dao;

    public SysResService() {
    }

    @Override
    protected IEntityDao<SysRes, Long> getEntityDao() {
        return dao;
    }

    public List<SysRes> getChildrenByAlias(String alias) {
        List<SysRes> childs = new ArrayList<>();
        if (StringUtil.isEmpty(alias)) {
            return childs;
        }
        SysRes sr = new SysRes();
        sr.setAlias(alias);
        try {
            List<SysRes> parentList = getAll(BeanUtils.convertBean2Map(sr));
            if (parentList.size() > 0) {
                childs = getByPid(parentList.get(0).getId());
            }
        } catch (Exception e) {
            log.error("bean转换Map出错", e);
        }
        return childs;
    }

    public List<SysRes> getByPid(Long pid) {
        if (pid == null) {
            return new ArrayList<>();
        }
        Map params = new HashMap(1);
        params.put("pid", pid);
        return getAll(params);
    }
}
