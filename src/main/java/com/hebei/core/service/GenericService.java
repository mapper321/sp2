package com.hebei.core.service;


import com.hebei.core.model.PageBean;
import com.hebei.core.model.ResultView;
import com.hebei.core.mybatis.IEntityDao;
import com.hebei.core.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;


public abstract class GenericService<T, PK extends Serializable> {

    private Logger log = LoggerFactory.getLogger(GenericService.class);

    protected abstract IEntityDao<T, PK> getEntityDao();

    public void add(Object entity) {
        getEntityDao().add(entity);
    }

    public void delById(PK id) {
        getEntityDao().delById(id);
    }

    public void delByIds(PK[] ids) {
        if (BeanUtils.isEmpty(ids))
            return;
        for (PK p : ids)
            delById(p);
    }

    public void update(T entity) {
        getEntityDao().update(entity);
    }

    public T getById(PK id) {
        return getEntityDao().getById(id);
    }

    public List<T> getList(String statatementName, PageBean pb) {
        List list = getEntityDao().getList(statatementName, pb);
        return list;
    }

    public List<T> getAll() {
        return getEntityDao().getAll();
    }

    public ResultView getAll(PageBean pb, Object params) {
        return getEntityDao().getAll(pb, params);
    }

}
