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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        page.setTotal(userService.getUserCount());
        List<User> userList = userService.getUserForPage(page);
        page.setRoot(userList);
        return page;
    }

    /**
     * 基于bootStrapTable的用户加载分页方法
     * @param limit
     * @param offset
     * @return
     */
    @RequestMapping("/getUserForTable")
    @ResponseBody
    public Object getUserForTable(int limit,int offset,String search){
        int start = (offset)*limit;
        Page page = new Page(start,limit);
        page.setCurrentPage(offset+1);
        page.setTotal(userService.getUserCount());
        List<User> userList;
        if(search!=""&&search!=null){
            userList = userService.getUserForPage(page, search);
        }else{
             userList= userService.getUserForPage(page);
        }
        page.setRoot(userList);
        JSONObject json = new JSONObject();
        json.put("total",page.getTotal());
        json.put("rows",page.getRoot());
        return json;
    }

    /**
     * 删除用户的Ajax方法
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public Object deleteUser(String id){
        boolean mark = userService.deleteUser(id);
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
        //先查询用户有的角色
        List<Role> roleList = userService.getRoleById(id);
        //再查询所有角色然后对用户有的角色进行排除
        List<Role> allRole = userService.getAllRole();
        //排除
        for(Role role:roleList){
            for(int i=0;i<allRole.size();i++){
                if(role.getId().equals(allRole.get(i).getId())){
                    allRole.remove(i);
                }
            }
        }
        Map<String,List<Role>> returnMap = new HashMap<>();
        returnMap.put("hasRole",roleList);
        returnMap.put("lackRole",allRole);
        JSONObject json = baseService.getAjaxResultHasObject(true,-1,"",returnMap);
        return json;
    }

    /**
     * 角色管理页面的表格数据请求
     * @param limit
     * @param offset
     * @return
     */
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

    /**
     * 角色绑定方法，直接添加user_role_tb的数据,参数应该均存在，否则无法传递，暂时不作存储过程
     * @param userid
     * @param roleid
     * @return
     */
    @RequestMapping("bindRole")
    @ResponseBody
    public Object bindRole(String userid,String roleid){
        boolean mark = userService.bindRole(userid, roleid);
        JSONObject json = baseService.getAjaxResult(mark,5002,"无法绑定");
        return json;
    }

    /**
     * 角色解绑方法，直接更新user_role_tb的数据,参数应该均存在，否则无法传递，暂时不作存储过程
     * @param userid
     * @param roleid
     * @return
     */
    @RequestMapping("reliveBindRole")
    @ResponseBody
    public Object reliveBindRole(String userid,String roleid){
        boolean mark = userService.reliveBindRole(userid, roleid);
        JSONObject json = baseService.getAjaxResult(mark,5002,"无法解绑");
        return json;
    }
}
