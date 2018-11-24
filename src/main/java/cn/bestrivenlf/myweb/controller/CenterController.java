package cn.bestrivenlf.myweb.controller;


import cn.bestrivenlf.myweb.entity.Note;
import cn.bestrivenlf.myweb.entity.TacModel;
import cn.bestrivenlf.myweb.interfaceService.CenterService;
import cn.bestrivenlf.myweb.interfaceService.NoteService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


/**
 * @Author: liufan
 * @Date: 2018/8/30 09:01
 * @Description: for center.html
 */
@Controller
@RequestMapping("/center")
public class CenterController {
    @Autowired
    private CenterService centerService;
    @Autowired
    private NoteService noteService;
    /**
     * center.html页面提交个人描述方法
     * @param idList
     * @param titleList
     * @param contentList
     * @param length
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveDescription")
    @ResponseBody
    public Object saveDescription( @RequestParam(value = "idList") String idList[], @RequestParam(value = "titleList") String  titleList[],  @RequestParam(value = "contentList")String contentList[],@RequestParam(value = "length")int length,@RequestParam(value = "mark") String mark)throws Exception {
        ArrayList<TacModel> tacModelList = new ArrayList<TacModel>();
        for(int i = 0;i<length;i++){
            TacModel t = new TacModel();
            if (!idList[i].equals("0")){
               t = new TacModel(idList[i]);
            }
            t.setTitle(titleList[i]);
            t.setContent(contentList[i]);
            t.setMark(Integer.parseInt(mark));
            tacModelList.add(t);
        }
        try {
            centerService.saveTacModel(tacModelList);
        }catch (Exception e){
            String m = e.getMessage();
            if(m.equals("IdNotZero")){
                return "IdNotZero";
            }else{
                throw e;
            }
        }
        return "success";
    }

    @RequestMapping(value = "/getDescription")
    @ResponseBody
    @Cacheable(cacheNames = {"description"},key = "#root.getMethodName()+'['+#mark+']'")
    public Object getDescription(@RequestParam(value = "mark") String mark){
        if (mark!=null){
             JSONArray jsonArray = centerService.getDescription(Integer.parseInt(mark));
            if (jsonArray!=null){
                return jsonArray;
            }else{
                return "error";
            }
        }else {
            return "error";
        }
    }

    @RequestMapping(value = "/entrance")
    public String entrance(Model model){
        return "backPages/center";
    }
}
