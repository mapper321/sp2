package com.hebei.core.web.servlet;

import com.hebei.core.service.template.MyGridFsTemplate;
import com.hebei.core.util.SpringUtils;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author: mapper
 * @since: 2020/4/2
 */
@Slf4j
public class GridFsServlet extends HttpServlet {

    public static final String URI_PREFIX = "/gridfs/";

    public static MyGridFsTemplate gridFsTemplate;

    @Override
    public void init() throws ServletException {
        super.init();
        gridFsTemplate = SpringUtils.getBean(MyGridFsTemplate.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = URLDecoder.decode(req.getRequestURI(), "utf-8");
        String dbName = url.substring(url.indexOf(URI_PREFIX) + URI_PREFIX.length(), url.lastIndexOf("/"));
        String objId = url.substring(url.lastIndexOf("/") + 1, url.contains(".") ? url.lastIndexOf(".") : url.length());
        Criteria all = null;
        if (StringUtils.isNumeric(objId)) {
            all = GridFsCriteria.where("_id").is(Long.valueOf(objId));
        } else {
            all = GridFsCriteria.where("_id").is(objId);
        }
        GridFSFile file = gridFsTemplate.findOne(new Query(all),dbName);
        //浏览器缓存配置
        if (file == null) {
            resp.setStatus(404); // Not Found
            return;
        }
        GridFsResource resource = gridFsTemplate.getResource(file,dbName);
        String contentType = file.getMetadata().getString("_contentType");
        resp.setContentType(getContentType(contentType));
        if (needCache(contentType)) {
            String modifiedSince = req.getHeader("If-Modified-Since");
            DateFormat df = new SimpleDateFormat(
                    "EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date uploadDate = file.getUploadDate();
            String lastModified = df.format(uploadDate);
            if (modifiedSince != null) {
                Date modifiedDate = null;
                Date sinceDate = null;
                try {
                    modifiedDate = df.parse(lastModified);
                    sinceDate = df.parse(modifiedSince);
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
                if (modifiedDate.compareTo(sinceDate) <= 0) {
                    resp.setStatus(304); // Not Modified
                    return;
                }
            }
            long maxAge = 10L * 365L * 24L * 60L * 60L; // ten years, in seconds
            resp.setHeader("Cache-Control", "max-age=" + maxAge);
            resp.setHeader("Last-Modified", lastModified);
            resp.setDateHeader("Expires", uploadDate.getTime() + maxAge
                    * 1000L);
        } else {
            resp.setHeader("Pragma", "no-cache");
            resp.setHeader("Cache-Control", "no-cache");
            resp.setDateHeader("Expires", 0);
        }
        try (GridFSDownloadStream inputStream = (GridFSDownloadStream) resource.getInputStream()) {
            byte[] buffer = new byte[4096];
            ServletOutputStream outputStream = resp.getOutputStream();
            int len = 0;
            while ((len = inputStream.read(buffer)) > -1) {
                outputStream.write(buffer, 0, len);
            }
        } catch (Exception e) {
            log.error("获取gridfs文件出错", e);
            resp.setStatus(404); // Not Found
            return;
        }
    }

    private String getContentType(String ext) {
        String type = null;
        if (ext == null) {
            type = "application/octet-stream";
        } else if (ext.equals("jpg")) {
            type = "image/jpeg";
        } else if (ext.equals("jpeg") || ext.equals("png") || ext.equals("gif")
                || ext.equals("bmp")) {
            type = "image/" + ext;
        } else if (ext.equals("html") || ext.equals("htm")) {
            type = "text/html; charset=utf-8";
        } else if (ext.equals("swf")) {
            type = "application/x-shockwave-flash";
        } else if (ext.equals("mp3")) {
            type = "media";
        } else if (ext.equals("mp4")) {
            type = "video/mp4";
        } else if (ext.equals("pdf")) {
            type = "application/pdf";
        } else {
            type = "application/octet-stream";
        }
        return type;
    }


    private boolean needCache(String ext) {// 判断文件是否需要缓存。
        if (ext == null) {
            return false;
        }
        boolean need = false;
        String[] arr = {"jpg", "jpeg", "png", "gif", "bmp", "html", "htm",
                "swf", "mp3", "mp4", "pdf"};
        for (String s : arr) {
            if (s.equals(ext)) {
                need = true;
                break;
            }
        }
        return need;
    }
}
