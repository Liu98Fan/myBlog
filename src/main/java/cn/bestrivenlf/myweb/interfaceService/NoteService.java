package cn.bestrivenlf.myweb.interfaceService;

import cn.bestrivenlf.myweb.entity.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/9/6 20:58
 * @Description:
 */
public interface NoteService {
    @CacheEvict(cacheNames = "Note",allEntries = true)
    @Transactional
    public Note saveNote(Note note,List<FileEntity> fileEntities)throws Exception;
    public int getNoteCount();
    public int getNoteCountByClassifyId(String classifyId);
    public int getNoteCountBySearch(String search);
    public List<Note> getNotesForPage(Page page);
    public List<Note> getNotesForPageByClassifyId(Page page,String classifyId);
    public List<Note> getNotesForPageBySearch(Page page,String search);
    public Note getNote(String id);
    @Cacheable(cacheNames = "Note",key = "#root.methodName")
    public List<NoteClassify> getNoteClassify();
    @Cacheable(cacheNames = "Note",key = "#root.methodName+'.'+#cid")
    public Integer getClassifyNumber(String cid);
    @Cacheable(cacheNames = "Note",key = "'MainPage.'+#root.methodName")
    public List<Note> getNoteByNumber(int number);
    @Cacheable(cacheNames = "Note",key = "#root.methodName")
    public List<NoteClassify> getClassifyNumberList();
    @CacheEvict(cacheNames = "Note",allEntries = true)
    public void clearCache();
    @CacheEvict(cacheNames = "Note",allEntries = true)
    public void deleteNote(String id);
    @Cacheable(cacheNames = "Note",key = "#root.methodName")
    public List<Note> getAllNote();
    //-------------------------------评论功能----------------------------------//
    public boolean saveComment(Comment comment);
    //public List<Comment> getCommentByNoteId(String noteId);
    public List<CommentPOJO> getCommentByNoteId(String noteId);
    public List<ReplyPOJO> getReplyListByCommentId(String commentId);
    public boolean saveReply(Reply reply);
    public boolean deleteComment(String commentId);
    @Cacheable(cacheNames = "Note",key = "#root.methodName+'('+#id+')'")
    public List<FileEntity> getResourceByNoteId(String id);

    public Boolean removeResourceById(String id,String noteId);
}
