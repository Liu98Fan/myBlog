package cn.bestrivenlf.myWeb.entity;

/**
 * @Author: liufan
 * @Date: 2018/10/15 20:09
 * @Description:
 */
public class NoteClassify extends BaseEntity {
    private String classifyname;
    private Integer number;


    public NoteClassify(String id) {
        super(id);
    }

    public NoteClassify() {
        super();
    }

    public String getClassifyname() {
        return classifyname;
    }

    public void setClassifyname(String classifyname) {
        this.classifyname = classifyname;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
