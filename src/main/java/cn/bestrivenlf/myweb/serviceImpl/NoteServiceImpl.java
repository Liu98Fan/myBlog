package cn.bestrivenlf.myweb.serviceImpl;

import cn.bestrivenlf.myweb.entity.*;
import cn.bestrivenlf.myweb.interfaceDao.NoteDao;
import cn.bestrivenlf.myweb.interfaceService.BaseService;
import cn.bestrivenlf.myweb.interfaceService.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/9/6 21:04
 * @Description:
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteDao notedao;
    @Autowired
    private BaseService baseService;
    @Override
    public Note saveNote(Note note,List<FileEntity> fileEntities)throws Exception {
        Note n =  notedao.saveNote(note);
        String noteId = note.getId();
        //遍历插入之前先清空关联
        //notedao.clearResourceByNoteId(noteId);
        for (FileEntity fileEntity:fileEntities){
            //遍历插入
            String mark = notedao.saveResourceByNoteId(fileEntity, noteId);
            if(!mark.equals("succ")){
                throw new Exception("资源保存失败");
            }
        }
        return n;
    }
    @Override
    public int getNoteCount(){
        return notedao.getNoteCount();
    }

    @Override
    public int getNoteCountByClassifyId(String classifyId) {
        return notedao.getNoteCountByClassifyId(classifyId);
    }

    @Override
    public int getNoteCountBySearch(String search) {
        return notedao.getNoteCountBySearch(search);
    }


    @Override
    public List<Note> getNotesForPage(Page page){
        int start = page.getCurrentResult();
        int end = start + page.getLimit();
        return notedao.getNotesForPage(start,end-start);
    }

    @Override
    public List<Note> getNotesForPageByClassifyId(Page page, String classifyId) {
        int start = page.getCurrentResult();
        int end = start + page.getLimit();
        return notedao.getNotesForPageByClassifyId(start,end-start,classifyId);
    }

    @Override
    public List<Note> getNotesForPageBySearch(Page page, String search) {
        int start = page.getCurrentResult();
        int end = start + page.getLimit();
        return notedao.getNotesForPageBySearch(start,end-start,search);
    }

    @Override
    public Note getNote(String id) {
        return notedao.getNote(id);
    }

    @Override
    public List<NoteClassify> getNoteClassify() {
        return notedao.getNoteClassify();
    }

    @Override
    public Integer getClassifyNumber(String cid) {
        return notedao.getClassifyNumber(cid);
    }

    @Override
    public List<Note> getNoteByNumber(int number) {
        List<Note> notelist = notedao.getNotesForPage(0,number);
        return notelist;
    }

    @Override
    public List<NoteClassify> getClassifyNumberList() {
        return notedao.getClassifyNumberList();
    }

    @Override
    public void clearCache() {
        System.out.println("删除成功");
    }

    @Override
    public void deleteNote(String id) {
        notedao.deleteNote(id);
    }

    @Override
    public List<Note> getAllNote() {
        return notedao.getAllNote();
    }

    @Override
    public boolean saveComment(Comment comment) {
        String returnValue = notedao.saveComment(comment);
        return returnValue.equals("succ")?true:false;
    }

    @Override
    public List<CommentPOJO> getCommentByNoteId(String noteId) {
        return notedao.getCommentByNoteId(noteId);
    }

    @Override
    public List<ReplyPOJO> getReplyListByCommentId(String commentId) {
        return notedao.getReplyListByCommentId(commentId);
    }

    @Override
    public boolean saveReply(Reply reply) {
        String returnValue = notedao.saveReply(reply);
        return returnValue.equals("succ")?true:false;
    }

    @Override
    public boolean deleteComment(String commentId) {
        String returnValue = notedao.deleteComment(commentId);
        return returnValue.equals("succ")?true:false;
    }

    @Override
    public List<FileEntity> getResourceByNoteId(String id) {
        return notedao.getResourceByNoteId(id);
    }

    @Override
    public Boolean removeResourceById(String id,String noteId) {
        String returnValue = notedao.removeResourceById(id,noteId);
       if(returnValue.equals("succ")){
           return true;
       }else{
           return false;
       }
    }

}
