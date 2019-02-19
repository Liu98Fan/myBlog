package cn.bestrivenlf.myweb.controller;

import cn.bestrivenlf.myweb.entity.FileEntity;
import cn.bestrivenlf.myweb.entity.LeaveMessage;
import cn.bestrivenlf.myweb.entity.User;
import cn.bestrivenlf.myweb.interfaceService.BaseService;
import cn.bestrivenlf.myweb.interfaceService.ToolService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Value("${file.noteResourceUploadPath}")
    String noteResourceUploadPath;
    @Value("${file.noteAccessPath}")
    String noteAccessPath;
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
    @ResponseBody
    @RequestMapping(value = "/uploadResources" ,method = RequestMethod.POST)
    public Map<String, Object> uploadResources(HttpServletRequest request, HttpServletResponse response,HttpSession session)
            throws Exception {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        System.out.println("成功上传一次");
        //初始化返回map
        Map<String, Object> json = new HashMap<String, Object>();
        //获取request
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFileMap().get("file_data");
        //获得上传的文件名
        String filename = multipartFile.getOriginalFilename();
        InputStream inputStream;
        //获取文件的后缀
        String type = filename.substring(filename.lastIndexOf("."));
        //生成新的随机名称（完整）
        String newFileName =baseService.getUuid()+type;
        String vitrualPath = noteAccessPath+newFileName;
        String absolutePath = noteResourceUploadPath+newFileName;
        String fileMd5 = toolService.getMd5(multipartFile);
        FileEntity fileEntity = new FileEntity();
        Map<String,Object> noteFileMap = null;
        try {
            //获得文件的流
            inputStream = multipartFile.getInputStream();
            fileEntity.setResourceName(filename);
            fileEntity.setResourceType(type);
            fileEntity.setMd5(fileMd5);
            fileEntity.setAbsolutePath(absolutePath);
            fileEntity.setVitrualPath(vitrualPath);
            json.put("status",true);
            json.put("message", "文件上传成功");

            FileEntity tmp = new FileEntity();
            BeanUtils.copyProperties(fileEntity,tmp);
            tmp.setFileStream(inputStream);
             noteFileMap= (Map<String,Object>)session.getAttribute("noteFileMap");
             if(null!=noteFileMap){
                 noteFileMap.put(tmp.getId(),tmp);
             }else{
                 noteFileMap = new HashMap<>();
                 noteFileMap.put(tmp.getId(),tmp);
             }
            json.put("object",fileEntity);
            session.setAttribute("noteFileMap",noteFileMap);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", false);
            json.put("message", "文件上传失败");
        }
        return json;
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
