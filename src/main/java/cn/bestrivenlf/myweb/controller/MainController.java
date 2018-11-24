package cn.bestrivenlf.myweb.controller;

import cn.bestrivenlf.myweb.entity.Note;
import cn.bestrivenlf.myweb.entity.SkillParent;
import cn.bestrivenlf.myweb.interfaceService.NoteService;
import cn.bestrivenlf.myweb.interfaceService.SkillService;
import cn.bestrivenlf.myweb.interfaceService.TraceService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/10/16 15:46
 * @Description:
 */
@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private TraceService traceService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SkillService skillService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("/entrance")
    public String entrance(Model model){
        ArrayList<Note> nlist = (ArrayList<Note>) noteService.getNoteByNumber(3);
        model.addAttribute("nlist",nlist);
        String message = "success";
        List<SkillParent> splist = new ArrayList<>();
        try {
            splist =skillService.getSkill();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            message = "fail";
        }finally {
            model.addAttribute("message",message);
            model.addAttribute("spList",splist);
        }
        JSONObject jsonObject = traceService.getTraceByCount(7);
        model.addAttribute("traceJson",jsonObject);
        //写入缓存
        return "index";
    }

}
