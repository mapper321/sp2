package com.hebei.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传后的信息
 * @author: mapper
 * @since: 2020/4/2
 */
@Data
public class UploadedFile implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 文件id
     */
    private Long id;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件大小
     */
    private String fileSize;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 上传人
     */
    private Long userid;
    /**
     * 上传时间
     */
    private Date createTime;
}
