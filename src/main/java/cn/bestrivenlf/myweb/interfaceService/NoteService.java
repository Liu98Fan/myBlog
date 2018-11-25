package cn.bestrivenlf.myweb.interfaceService;

import cn.bestrivenlf.myweb.entity.Note;
import cn.bestrivenlf.myweb.entity.NoteClassify;
import cn.bestrivenlf.myweb.entity.Page;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/9/6 20:58
 * @Description:
 */
public interface NoteService {
    @CacheEvict(cacheNames = "Note",allEntries = true)
    public Note saveNote(Note note);
    public int getNoteCount();
    public List<Note> getNotesForPage(Page page);
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
}
