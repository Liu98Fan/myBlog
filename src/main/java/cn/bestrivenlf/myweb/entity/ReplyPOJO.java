package cn.bestrivenlf.myweb.entity;

/**
 * @author:LIUFAN
 * @date:2019/1/4
 */
public class ReplyPOJO extends BaseEntity {
    private Reply reply;
    private User user;

    public ReplyPOJO() {
        super();
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
