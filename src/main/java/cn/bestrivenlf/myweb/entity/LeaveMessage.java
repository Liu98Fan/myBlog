package cn.bestrivenlf.myweb.entity;

/**
 * @author:LIUFAN
 * @date:2018/11/22
 */
public class LeaveMessage extends TacModel {
    private String userid;

    public LeaveMessage() {
        super();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
