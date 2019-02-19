package cn.bestrivenlf.myweb.controller;

import cn.bestrivenlf.myweb.entity.*;
import cn.bestrivenlf.myweb.interfaceService.BaseService;
import cn.bestrivenlf.myweb.interfaceService.NoteService;
import cn.bestrivenlf.myweb.interfaceService.ToolService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private BaseService baseService;
    @Autowired
    private ToolService toolService;
    @RequestMapping(value="/saveNote")
    public String saveNote(Note note, String fileList, HttpSession session)throws Exception{
        if (note.getId()=="") {
            note.setId(baseService.getUuid());
        }
        if(note.getNoteclassify().getId().length()<32){
            note.getNoteclassify().setId(baseService.getUuid());
        }
        JSONObject fileJson = JSONObject.fromObject(fileList);

        List<String> idLst =(List<String>) fileJson.get("fileList");
        Map<String,Object> fileMap = (Map<String,Object>)session.getAttribute("noteFileMap");
        List<FileEntity> fileEntities = new ArrayList<>();
        if(null!=fileMap){
            for(String id:idLst){
                FileEntity fileEntity = null;
                for(String key:fileMap.keySet()){
                    if(key.equals(id)){
                        fileEntity = (FileEntity) fileMap.get(key);
                        break;
                    }
                }
                if(null!=fileEntity){
                   fileEntities.add(fileEntity);
                }
            }
            Boolean isSuccess = toolService.saveFile(fileEntities);
            if(isSuccess){
                System.out.println("文件保存成功，接下来进行数据库保存");
            }
        }
        note.setNewdate(note.getDate());
        Note n = noteService.saveNote(note,fileEntities);
        return "redirect:/note/entrance";
    }
    @RequestMapping(value = "/delete/{id}")
    public String deleteNote(@PathVariable(value = "id") String id){
        //感觉我自己是个傻子
        noteService.deleteNote(id);
        return "redirect:/note/entrance";
    }

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

    @RequestMapping(value="/getNotePageByClassifyId")
    @ResponseBody
    @Cacheable(cacheNames = "Note",key = "#root.methodName+'('+#nowPage+','+#limit+','+#classifyId+')'")
    public Object getNotePageByClassifyId(String classifyId,Integer start, Integer limit, Integer nowPage, Integer Number){
        Page page = new Page(start,limit);
        page.setCurrentPage(nowPage);
        page.setTotal(noteService.getNoteCountByClassifyId(classifyId));
        List<Note> noteList = noteService.getNotesForPageByClassifyId(page,classifyId);
        page.setRoot(noteList);
        return page;
    }

    @RequestMapping(value="/getNotePageBySearch")
    @ResponseBody
    @Cacheable(cacheNames = "Note",key = "#root.methodName+'('+#nowPage+','+#limit+','+#search+')'")
    public Object getNotePageBySearch(String search,Integer start, Integer limit, Integer nowPage, Integer Number){
        Page page = new Page(start,limit);
        page.setCurrentPage(nowPage);
        page.setTotal(noteService.getNoteCountBySearch(search));
        List<Note> noteList = noteService.getNotesForPageBySearch(page,search);
        page.setRoot(noteList);
        return page;
    }

    @RequestMapping(value = "/getNote/{id}")
    public String noteDisplay(@PathVariable(value = "id") String id, Model model){
        Note note = noteService.getNote(id);
        List<CommentPOJO> commentList = noteService.getCommentByNoteId(id);
        List<FileEntity> fileList = noteService.getResourceByNoteId(id);
        model.addAttribute("fileList",fileList);
        model.addAttribute(note);
        model.addAttribute("commentList",commentList);
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
        List<FileEntity> fileList = noteService.getResourceByNoteId(id);
        model.addAttribute("fileList",fileList);
        return "backPages/note";
    }


    @RequestMapping("/getNoteList")
    public String getNoteList(Model model){
        model.addAttribute("clist",noteService.getClassifyNumberList());
        return "noteList";
    }

    @RequestMapping("/getResourceList")
    public String getResourceList(Model model){

        return "resourceList";
    }
    @ResponseBody
    @RequestMapping("/removeResourceById")
    public Object removeResourceById(String id,String noteId){
        Boolean mark = noteService.removeResourceById(id,noteId);
        return baseService.getAjaxResult(mark,5001,"删除失败，请稍后重试");
    }

}
