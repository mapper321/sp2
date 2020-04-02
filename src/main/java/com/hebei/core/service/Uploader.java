package com.hebei.core.service;

import com.hebei.core.model.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 * 文件上传
 * @author: mapper
 * @since: 2020/4/2
 */
interface Uploader {
    /**
     *
     * @param: [in, source 文件夹名称, fileName 文件名]
     * @return: UploadedFile  文件信息
     * @author: mapper
     * @since: 2020/4/2
     */
    UploadedFile upload(InputStream in,String source,String fileName);

    /**
     * 【功能描述】: 批量上传<br>
     * @param: [request, source 文件夹名称]
     * @return: java.util.List<UploadedFile> 按顺序返回上传后的文件信息列表
     * @author: mapper
     * @since: 2020/4/2
     */
    List<UploadedFile> upload(MultipartFile[] files, String source);

    /**
     *
     * @param: [file, source]
     * @return: UploadedFile  文件信息
     * @author: mapper
     * @since: 2020/4/2
     */
    UploadedFile upload(MultipartFile file, String source);

}
