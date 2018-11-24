package cn.bestrivenlf.myweb.controller;

import cn.bestrivenlf.myweb.entity.Page;
import cn.bestrivenlf.myweb.entity.Role;
import cn.bestrivenlf.myweb.entity.User;
import cn.bestrivenlf.myweb.interfaceService.BaseService;
import cn.bestrivenlf.myweb.interfaceService.ManagerService;
import cn.bestrivenlf.myweb.interfaceService.UserService;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author:LIUFAN
 * @date:2018/11/22
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private UserService userService;

    /**
     * 加载用户管理数据
     * @return 后台用户管理页面
     */
    //@RequiresPermissions({"manager:userManager"})
    @RequestMapping("/userManager")
    public String userManager(Model model){
        return "backPages/userManager";
    }
    @RequestMapping("/roleManager")
    public String roleManager(Model model){
        return "backPages/roleManager";
    }
    @RequestMapping("/authorityManager")
    public String authorityManager(Model model){
        return "backPages/authorityManager";
    }

    /**
     * 用户加载分页方法
     * @param start
     * @param limit
     * @param nowPage
     * @param Number
     * @return
     */
    @RequestMapping("/getUserForPage")
    @ResponseBody
    public Object getUser(Integer start, Integer limit, Integer nowPage, Integer Number){
        Page page = new Page(start,limit);
        page.setCurrentPage(nowPage);
        page.setTotal(managerService.getUserCount());
        List<User> userList = managerService.getUserForPage(page);
        page.setRoot(userList);
        return page;
    }

    /**
     * 删除用户的Ajax方法
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public Object deleteUser(String id){
        boolean mark = managerService.deleteUser(id);
        JSONObject json = baseService.getAjaxResult(mark, 5001, "删除失败，请重试。");
        return json;
    }

    /**
     * 查询用户角色信息Ajax方法
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/listRoleById")
    public Object listRoleById(String id){
        List<Role> roleList = userService.getRoleById(id);
        JSONObject json = baseService.getAjaxResultHasObject(true,-1,"",roleList);
        return json;
    }
    @ResponseBody
    @RequestMapping("/getRoles")
    public Object getRolesForPage(int limit,int offset){
        int start = (offset)*limit;
        Page page = new Page(start,limit);
        page.setCurrentPage(offset+1);
        page.setTotal(userService.getRoleCount());
        List<Role> roleList = userService.getRoleForPage(page);
        page.setRoot(roleList);
        JSONObject json = new JSONObject();
        json.put("total",page.getTotal());
        json.put("rows",page.getRoot());
        return json;
    }
}
