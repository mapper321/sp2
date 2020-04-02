package com.hebei.api;

import com.hebei.core.model.ResultView;
import com.hebei.core.model.UploadedFile;
import com.hebei.core.service.impl.MongoUploader;
import com.hebei.core.service.template.MyGridFsTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: mapper
 * @since: 2020/4/2
 */
@RestController
@RequestMapping("/api/upload")
@Api(tags = "文件上传")
@Slf4j
public class UploadController {

    @Autowired
    MongoUploader mongoUploader;

    @PostMapping("/mongo")
    @ApiOperation(value = "上传文件到mongodb")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "folder",value = "所属文件夹,不填默认为default",paramType = "query",required = false)
    })
    public ResultView<UploadedFile> upload(MultipartFile file, String folder) {
        return ResultView.ok(mongoUploader.upload(file,folder));
    }

}
