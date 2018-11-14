package cn.bestrivenlf.myWeb.controller;

import cn.bestrivenlf.myWeb.entity.Note;
import cn.bestrivenlf.myWeb.entity.NoteClassify;
import cn.bestrivenlf.myWeb.entity.Page;
import cn.bestrivenlf.myWeb.interfaceService.NoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: liufan
 * @Date: 2018/9/6 20:57
 * @Description:
 */
@Controller
@RequestMapping(value = "/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @RequestMapping(value="/saveNote")
    public String saveNote(Note note){
        if (note.getId()=="") {
            note.setId(UUID.randomUUID().toString().replace("-", ""));
        }
        if(note.getNoteclassify().getId().length()<32){
            note.getNoteclassify().setId(UUID.randomUUID().toString().replace("-", ""));
        }
        note.setNewdate(note.getDate());
        Note n = noteService.saveNote(note);
        return "redirect:/note/entrance";
    }
    @RequestMapping(value = "/delete/{id}")
    public String deleteNote(@PathVariable(value = "id") String id){
//        Note note = noteService.getNote(id);
//        if(note!=null) {
//            note.setNewdate(note.getDate());
//            note.setDel_flag(1);
//        }
//        noteService.saveNote(note);
        //感觉我自己是个傻子
        noteService.deleteNote(id);
        return "redirect:/note/entrance";
    }
//    @RequestMapping(value = "/modifyNote")
//    public String modifyNote(Note note){
//        note.setNewdate(note.getDate());
//        noteService.saveNote(note);
//        return null;
//    }

    @RequestMapping(value="/getNotePage")
    @ResponseBody
    @Cacheable(cacheNames = "Note",key = "#root.methodName+'('+#nowPage+','+#limit+')'")
    public Object getNotePage(Integer start, Integer limit, Integer nowPage, Integer Number){
        Page page = new Page(start,limit);
        page.setCurrentPage(nowPage);
        page.setTotal(noteService.getNoteCount());
        List<Note> noteList = noteService.getNotesForPage(page);
        page.setRoot(noteList);
        return page;
    }

//    @RequestMapping(value = "/noteEntrance")
//    public String noteEntrance(){
//
//        return "/backPages/note";
//    }
    @RequestMapping(value = "/getNote/{id}")
    public String noteDisplay(@PathVariable(value = "id") String id, Model model){
        Note note = noteService.getNote(id);
        model.addAttribute(note);
        return "noteDisplay";
    }

    @RequestMapping("/entrance")
    public String entrance(Model model){
        ArrayList<NoteClassify> clist = (ArrayList<NoteClassify>) noteService.getNoteClassify();
        model.addAttribute("clist",clist);
        return "backPages/note";
    }

    @RequestMapping(value = "/editor/{id}")
    public String editorNote(@PathVariable(value = "id") String id,Model model){
        Note note = noteService.getNote(id);
        model.addAttribute(note);
        ArrayList<NoteClassify> clist = (ArrayList<NoteClassify>) noteService.getNoteClassify();
        model.addAttribute("clist",clist);
        return "backPages/note";
    }


    @RequestMapping("/getNoteList")
    public String getNoteList(Model model){
        model.addAttribute("clist",noteService.getClassifyNumberList());
        return "noteList";
    }
}
