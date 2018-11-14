package cn.bestrivenlf.myWeb.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/10/29 14:45
 * @Description:
 */
public class SkillParent extends BaseEntity {
    String name;//名字
    int mark;//标记 0是p 1是son
    String newdate;//更新时间
    String description;//描述
    List<SkillSon> son;
    int sonNumber = 0;


    public SkillParent(String id) {
        super(id);
        son = new ArrayList<SkillSon>();
    }

    public SkillParent() {
        son = new ArrayList<SkillSon>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getNewdate() {
        return newdate;
    }

    public void setNewdate(String newdate) {
        this.newdate = newdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SkillSon> getSon() {
        return son;
    }

    public void setSon(List<SkillSon> son) {
        this.son = son;
    }

    public int getSonNumber() {
        return sonNumber;
    }

    public void setSonNumber(int sonNumber) {
        this.sonNumber = sonNumber;
    }
}
