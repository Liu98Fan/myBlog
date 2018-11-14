package cn.bestrivenlf.myWeb.controller;

import cn.bestrivenlf.myWeb.entity.SkillParent;
import cn.bestrivenlf.myWeb.entity.SkillSon;
import cn.bestrivenlf.myWeb.interfaceService.SkillService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/10/7 20:31
 * @Description:
 */
@Controller
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    SkillService skillService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("/entrance")
    public String entrance(Model model){
        //此处来获取初始数据
        //首先应当get出来SkillP的数据
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
        return "backPages/skill";
    }

    @RequestMapping("/saveSkill")
    @ResponseBody
    public Object saveSkill(String jsonData){
        List<SkillParent> spList = skillService.parseJson(jsonData);
        int mark = skillService.saveSkill(spList);
        return mark;
    }
}
