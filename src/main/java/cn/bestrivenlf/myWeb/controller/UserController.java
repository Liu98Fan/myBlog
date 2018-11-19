package cn.bestrivenlf.myWeb.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpSession session, HttpServletRequest request){
        String servername = request.getServerName();
        int port = request.getServerPort();
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            try{
                UsernamePasswordToken token = new UsernamePasswordToken(username,password);
                subject.login(token);
                //下面进入realm
                SavedRequest savedRequest = WebUtils.getSavedRequest(request);//得到被拦截的url
                if(savedRequest==null)
                    return "/main/entrance";
                else
                    return "redirect://"+servername+":"+port+savedRequest.getRequestUrl();
            }catch (UnknownAccountException e){
                model.addAttribute("msg","用户不存在");
            }catch (IncorrectCredentialsException e){
                model.addAttribute("msg","用户名或密码错误");
            }catch (Exception e){
                model.addAttribute("msg","未知错误:"+e.getMessage());
            }
            return "login";
        }

        return "index";
    }
}
