package cn.bestrivenlf.myweb.controller;

import cn.bestrivenlf.myweb.entity.Comment;
import cn.bestrivenlf.myweb.entity.Reply;
import cn.bestrivenlf.myweb.entity.ReplyPOJO;
import cn.bestrivenlf.myweb.entity.User;
import cn.bestrivenlf.myweb.interfaceService.BaseService;
import cn.bestrivenlf.myweb.interfaceService.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author:LIUFAN
 * @date:2019/1/3
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private NoteService noteService;
    //----------------------------------评论功能--------------------------------------//

    /**
     * 添加笔记评论
     * @param comment
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveComment")
    public Object saveComment(Comment comment){
        //获得userId
        String userId = baseService.getCurrentUser().getId();
        comment.setUserId(userId);
        //调用service方法尝试存储本次评论
        Boolean mark = noteService.saveComment(comment);
        return baseService.getAjaxResultHasObject(mark,5001,"评论失败，数据插入异常",baseService.getCurrentUser());
    }

    /**
     * 查询评论回复
     * @return
     */
    @ResponseBody
    @RequestMapping("/getReplyListByCommentId")
    public Object getReplyListByCommentId(String commentId){
        //System.out.println(commentId);
        List<ReplyPOJO> replyPOJOList = noteService.getReplyListByCommentId(commentId);
        return baseService.getAjaxResultHasObject(true,-1,"不可能发生的错误",replyPOJOList);
    }

    /**
     * 保存回复
     * @param reply
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveReply")
    public Object saveReply(Reply reply){
        User user = baseService.getCurrentUser();
        String userId = user.getId();
        reply.setUserId(userId);
        boolean mark = noteService.saveReply(reply);
        return baseService.getAjaxResultHasObject(mark,5001,"插入失败，请稍后重试",user);
    }

    /**
     * 删除评论
     * @param commentId 评论id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteComment")
    public Object deleteComment(String commentId){
        boolean mark = noteService.deleteComment(commentId);
        return baseService.getAjaxResult(mark,5001,"删除失败，请稍后重试");
    }
}
