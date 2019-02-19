package cn.bestrivenlf.myweb.entity;

import java.util.List;

/**
 * @author:LIUFAN
 * @date:2019/1/2
 */
public class Comment extends BaseEntity {
    /**
     * 笔记Id
     */
    private String noteId;
    /**
     * 评论者Id
     */
    private String userId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 纯文本评论内容
     */
    private String textContent;
    /**
     * 回复列表
     */
    private List<Reply> replyList;
    /**
     * 点赞数
     */
    private int agree ;

    public Comment() {
        super();
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }
}
