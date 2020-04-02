package com.hebei.core.service;

import com.hebei.core.model.UploadedFile;
import com.hebei.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: mapper
 * @since: 2020/4/2
 */
@Slf4j
public abstract class AbstractUploader implements Uploader {

    public static final String DEFAULT_SOURCE = "default";

    @Override
    public List<UploadedFile> upload(MultipartFile[] files, String source) {
        List<UploadedFile> results = new ArrayList<>();
        for (MultipartFile f : files) {
            results.add(upload(f, source));
        }
        return results;
    }

    @Override
    public UploadedFile upload(MultipartFile file, String source) {
        String fileName = file.getOriginalFilename();
        UploadedFile result = new UploadedFile();
        if (StringUtil.isEmpty(source)) {
            source = DEFAULT_SOURCE;
        }
        try (InputStream inputStream = file.getInputStream()) {
            result = upload(inputStream, source, fileName);
        } catch (IOException e) {
            log.error("文件流获取错误", e);
        }
        return result;
    }

    @Override
    public UploadedFile upload(InputStream in, String source, String fileName) {
        UploadedFile result = new UploadedFile();
        result.setFileName(fileName);
        result.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
        result.setCreateTime(new Date());
        try {
            result.setFileSize(in.available() + "");
            result.setFilePath(uploadImpl(in, source, fileName));
        } catch (IOException e) {
            log.error("文件流获取错误", e);
        }
        return result;
    }

    /**
     * 【功能描述】: 文件上传具体实现<br>
     *
     * @param: [in, source, fileName]
     * @return: java.lang.String 返回上传后的文件路径
     * @author: mapper
     * @since: 2020/4/2
     */
    public abstract String uploadImpl(InputStream in, String source, String fileName);
}
