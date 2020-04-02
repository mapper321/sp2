package com.hebei.api;

import com.hebei.core.model.ResultView;
import com.hebei.core.model.UploadedFile;
import com.hebei.core.service.impl.MongoUploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
            @ApiImplicitParam(name = "folder", value = "所属文件夹,不填默认为default", paramType = "query", required = false)
    })
    public ResultView<UploadedFile> upload(MultipartFile file, String folder) {
        return ResultView.ok(mongoUploader.upload(file, folder));
    }

}
