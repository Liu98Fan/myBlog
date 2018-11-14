package cn.bestrivenlf.myWeb.serviceImpl;

import cn.bestrivenlf.myWeb.entity.Note;
import cn.bestrivenlf.myWeb.entity.NoteClassify;
import cn.bestrivenlf.myWeb.entity.Page;
import cn.bestrivenlf.myWeb.interfaceDao.NoteDao;
import cn.bestrivenlf.myWeb.interfaceService.BaseService;
import cn.bestrivenlf.myWeb.interfaceService.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
