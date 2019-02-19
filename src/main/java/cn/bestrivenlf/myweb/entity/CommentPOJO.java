package cn.bestrivenlf.myweb.entity;

import java.util.List;

/**
 * @author:LIUFAN
 * @date:2019/1/3
 */
public class CommentPOJO extends BaseEntity{
    private User user;
    private Comment comment;
    private List<Reply> replyList;
    private int replyCount;

    public CommentPOJO() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }
}
