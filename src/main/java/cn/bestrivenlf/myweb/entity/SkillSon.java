package cn.bestrivenlf.myweb.entity;

/**
 * @Author: liufan
 * @Date: 2018/10/29 14:48
 * @Description:
 */
public class SkillSon extends BaseEntity{
    String pid;
    String name;
    int num;
    String description;
    String newdate;
    int mark;

    public SkillSon(String id) {
        super(id);
    }

    public SkillSon() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewdate() {
        return newdate;
    }

    public void setNewdate(String newdate) {
        this.newdate = newdate;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
