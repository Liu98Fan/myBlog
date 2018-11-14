package cn.bestrivenlf.myWeb.entity;



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
}
