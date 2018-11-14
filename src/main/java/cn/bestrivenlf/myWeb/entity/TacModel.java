package cn.bestrivenlf.myWeb.entity;
//title and content模型
public class TacModel extends BaseEntity {
    private int mark;
    private String title;
    private String content;

    public TacModel(String id) {
        super(id);
    }

    public TacModel() {
        super();
    }

    public TacModel(int mark, String title, String content) {
        super();
        this.mark = mark;
        this.title = title;
        this.content = content;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
