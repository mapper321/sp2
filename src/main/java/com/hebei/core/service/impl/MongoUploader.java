package com.hebei.core.service.impl;

import com.hebei.core.service.AbstractUploader;
import com.hebei.core.service.template.MyGridFsTemplate;
import com.hebei.core.util.StringUtil;
import com.hebei.core.web.servlet.GridFsServlet;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * mongodb文件上传
 * @author: mapper
 * @since: 2020/4/2
 */
@Component
public class MongoUploader extends AbstractUploader {

    @Autowired
    MyGridFsTemplate myGridFsTemplate;

    public String uploadImpl(InputStream in, String source, String fileName){
        String path = null;
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        ObjectId objectId = myGridFsTemplate.store(in, fileName,fileType, source);
        path = GridFsServlet.URI_PREFIX+source+"/"+objectId+"."+fileType;
        return path;
    }
}
