package cn.bestrivenlf.myweb.controller;

import cn.bestrivenlf.myweb.entity.LeaveMessage;
import cn.bestrivenlf.myweb.entity.User;
import cn.bestrivenlf.myweb.interfaceService.BaseService;
import cn.bestrivenlf.myweb.interfaceService.ToolService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/10/7 21:13
 * @Description:
 */
@Controller
@RequestMapping("/tools")
public class ToolsController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private ToolService toolService;
    @Value("${file.uploadPath}")
    String uploadPath;

    @Value("${file.accessPath}")
    String accessPath;

    @RequestMapping("/uploadImages")
    @ResponseBody
    public JSONObject uploadImages(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,
                                   HttpServletRequest request, HttpServletResponse response){
        String trueFileName = file.getOriginalFilename();

        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));

        String fileName = System.currentTimeMillis()+"_"+RandomStringUtils.randomNumeric(5) +suffix;

        String path = uploadPath;
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject res = new JSONObject();
        res.put("url", accessPath+fileName);
        res.put("success", 1);
        res.put("message", "upload success!");

        return res;

    }
    @RequestMapping("/sendMail")
    @ResponseBody
    public Object sendMail(String name,String email,String message){
        if ((boolean)baseService.sendMail(name,email,message)){
            return "succ";
        }else{
            return "fail";
        }

    }

    @RequestMapping("/leaveMessage")
    public String leaveMessage(Model model, @RequestParam(value = "msg",defaultValue = "defalut") String msg){
        if(msg.equals("succ")){
            model.addAttribute("msg","提交成功");
        }else if(msg.equals("fail")){
            model.addAttribute("msg","提交失败");
        }

        List<LeaveMessage> messageList = toolService.getMessage();
        model.addAttribute("messageList",messageList);
        return "leaveMessage";
    }

    @RequestMapping("/getMessage")
    @ResponseBody
    public Object getMessage(){
        List<LeaveMessage> messageList = toolService.getMessage();
        return messageList;
    }
    @RequestMapping("/saveMessage")
    public String saveMessage(LeaveMessage message,HttpSession session){
        User user = (User)session.getAttribute("currentUser");
        String msg = "defalut";
        try {
            String id = user.getId();
            message.setUserid(id);
            toolService.saveMessage(message);
            msg = "succ";
            return "redirect:/tools/leaveMessage?msg="+msg;
        }catch (NullPointerException e){
            msg = "fail";
            return "redirect:/tools/leaveMessage?msg="+msg;
        }


    }
}
