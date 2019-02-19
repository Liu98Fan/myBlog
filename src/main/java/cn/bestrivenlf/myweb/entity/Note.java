package cn.bestrivenlf.myweb.entity;


import java.io.File;
import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/9/6 21:00
 * @Description:
 */
//@Document(indexName = "www.bestrivenlf.cn",type = "Note")
public class Note extends TacModel {
    private String author;
    private String newdate;
    private NoteClassify noteclassify;
    private List<FileEntity> resourceList;
    private int resourceCount;
    public Note(String id) {
        super(id);
    }
    public Note() {
        super();
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNewdate() {
        return newdate;
    }

    public void setNewdate(String modifyDate) {
        this.newdate = modifyDate;
    }

    public NoteClassify getNoteclassify() {
        return noteclassify;
    }

    public void setNoteclassify(NoteClassify noteclassify) {
        this.noteclassify = noteclassify;
    }

    public List<FileEntity> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<FileEntity> resourceList) {
        this.resourceList = resourceList;
    }

    public int getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(int resourceCount) {
        this.resourceCount = resourceCount;
    }
}
