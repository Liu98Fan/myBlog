package cn.bestrivenlf.myweb.serviceImpl;

import cn.bestrivenlf.myweb.entity.Note;
import cn.bestrivenlf.myweb.entity.NoteClassify;
import cn.bestrivenlf.myweb.entity.Page;
import cn.bestrivenlf.myweb.interfaceDao.NoteDao;
import cn.bestrivenlf.myweb.interfaceService.BaseService;
import cn.bestrivenlf.myweb.interfaceService.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Note saveNote(Note note) {
        Note n =  notedao.saveNote(note);
        return n;
    }
    public int getNoteCount(){
        return notedao.getNoteCount();
    }
    public List<Note> getNotesForPage(Page page){
        int start = page.getCurrentResult();
        int end = start + page.getLimit();
        return notedao.getNotesForPage(start,end);
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
}
