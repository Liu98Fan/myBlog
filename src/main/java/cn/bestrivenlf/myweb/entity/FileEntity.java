package cn.bestrivenlf.myweb.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * @author:LIUFAN
 * @date:2019/1/9
 */
public class FileEntity extends BaseEntity {
    /**
     * 资源名称，不包含后缀
     */
    String resourceName;
    /**
     * 资源类型，包含‘.’
     */
    String resourceType;
    /**
     * 虚拟路径，即项目访问路径，包含完整的资源名
     */
    String vitrualPath;
    /**
     * 真实路径，即磁盘地址，包含完整的资源名称
     */
    String absolutePath;
    /**
     * md5值，用来进行校验
     */
    String md5;
    /**
     * 资源描述，可能为null
     */
    String description;
    /**
     * 文件资源流
     */
    InputStream fileStream = null;

    public FileEntity() {
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resouceType) {
        this.resourceType = resouceType;
    }

    public String getVitrualPath() {
        return vitrualPath;
    }

    public void setVitrualPath(String vitrualPath) {
        this.vitrualPath = vitrualPath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputStream getFileStream() {
        return fileStream;
    }

    public void setFileStream(InputStream fileStream) {
        this.fileStream = fileStream;
    }
}

