package cn.bestrivenlf.myWeb.entity;

import net.sf.json.JSONObject;

/**
 * @Author: liufan
 * @Date: 2018/11/10 12:41
 * @Description:
 */
public class Trace extends BaseEntity {
    private String key;//标识关键操作
    private String content;//日迹内容
    private String newdate;
    private String author;
    private String information;//用来存储一些相关信息，格式是json
    private int mark;//操作标记
    private JSONObject jsonInfor;

    public Trace() {
        super();
    }

    public Trace(String id) {
        super(id);
    }

    public JSONObject getJsonInfor() {
        return jsonInfor;
    }

    public void setJsonInfor(JSONObject jsonInfor) {
        this.jsonInfor = jsonInfor;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewdate() {
        return newdate;
    }

    public void setNewdate(String newdate) {
        this.newdate = newdate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
