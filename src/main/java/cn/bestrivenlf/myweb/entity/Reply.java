package cn.bestrivenlf.myweb.entity;

/**
 * @author:LIUFAN
 * @date:2019/1/2
 */
public class Reply extends BaseEntity{
    /**
     * 评论Id
     */
    private String commentId;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 序列，标识评论下第几个回复
     */
    private int sequence;
    /**
     * 回复内容
     */
    private String content;
    /**
     * 回复头，形如"回复@张三:"
     */
    private String replyTo;

    public Reply() {
        super();
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }
}
