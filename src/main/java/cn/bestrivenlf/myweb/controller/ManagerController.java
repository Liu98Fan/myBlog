package cn.bestrivenlf.myweb.controller;

import cn.bestrivenlf.myweb.entity.*;
import cn.bestrivenlf.myweb.interfaceService.BaseService;
import cn.bestrivenlf.myweb.interfaceService.ManagerService;
import cn.bestrivenlf.myweb.interfaceService.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
        int start = offset;
        Page page = new Page(start,limit);
        page.setCurrentPage(offset==0?1:offset/limit);
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
    public Object getRolesForTable(int limit,int offset){
        int start = offset;
        Page page = new Page(start,limit);
        page.setCurrentPage(offset==0?1:offset/limit);
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
    @RequestMapping("/bindRole")
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
    @RequestMapping("/reliveBindRole")
    @ResponseBody
    public Object reliveBindRole(String userid,String roleid){
        boolean mark = userService.reliveBindRole(userid, roleid);
        JSONObject json = baseService.getAjaxResult(mark,5002,"无法解绑");
        return json;
    }

    /**
     * 获取权限（所有的url地址）的AJAX请求
     * 在这个方法前会先执行一下更新数据库中的url操作
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAuthorityForTable")
    public Object getAuthorityForTable(int limit,int offset){
        int start = offset;
        Page page = new Page(start,limit);
        page.setCurrentPage(offset==0?1:offset/limit);
        page.setTotal(managerService.getAuthorityCount());
        List<UrlMapping> roleList = managerService.getAuthorityForTable(page);
        page.setRoot(roleList);
        JSONObject json = new JSONObject();
        json.put("total",page.getTotal());
        json.put("rows",page.getRoot());
        return json;
    }

    /**
     * 父路径获取
     * @param limit
     * @param offset
     * @return
     */
    @Cacheable(cacheNames = "permission",key = "'parentAuthority('+#limit+','+#offset+')'")
    @ResponseBody
    @RequestMapping("/getParentAuthorityForTable")
    public Object getParentAuthorityForTable(int limit,int offset){
        int start = offset;
        Page page = new Page(start,limit);
        page.setCurrentPage(offset==0?1:offset/limit);
        page.setTotal(managerService.getParentAuthorityCount());
        List<ParentPermission> parentPermissionList = managerService.getParentAuthorityForTable(page);
        page.setRoot(parentPermissionList);
        JSONObject json = new JSONObject();
        json.put("total",page.getTotal());
        json.put("rows",page.getRoot());
        return json;
    }

    /**
     * 子路径获取
     * @param parentid
     * @param parentMapping
     * @return
     */
    @Cacheable(cacheNames = "permission",key = "'sonAuthority.'+#parentid")
    @ResponseBody
    @RequestMapping("/getSonAuthorityForTable")
    public Object getSonAuthorityForTable(String parentid,String parent){
        JSONObject json = new JSONObject();
        json.put("rows",managerService.getSonAuthorityForTable(parentid));
        return json;
    }

    /**
     * Ajax请求 存储/更新 父url数据
     * @param describe
     * @param isAuthority
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveParentUrl")
    public Object saveParentUrl(UrlMapping parentUrl){
        boolean mark = managerService.saveParentUrl(parentUrl);
        JSONObject json = baseService.getAjaxResult(mark,5003,"无法保存父节点");
        return json;
    }

    /**
     * 权限管理页面的添加权限
     * @param permission
     * @return
     */
   // @RequiresPermissions("permission:insertPermission")
    @CacheEvict(cacheNames = "permission",allEntries = true)
    @ResponseBody
    @RequestMapping("/savePermission")
    public Object savePermission(Permission permission){
        //首先获取父节点名称
        String parent = permission.getPermission().split(":")[0];
        permission.setParent(parent);
        permission.setNewdate(permission.getDate());
        boolean mark = userService.savePermission(permission);
        JSONObject json = baseService.getAjaxResult(mark,5004,"无法保存权限");
        return json;

    }

    /**
     * 权限管理页面对父路经的编辑操作
     * @param parentPermission
     * @return
     */
    @CacheEvict(cacheNames = "permission",allEntries = true)
    @ResponseBody
    @RequestMapping("/saveParentPermission")
    public Object saveParentPermission(ParentPermission parentPermission){
        boolean mark = userService.saveParentPermission(parentPermission);
        JSONObject json = baseService.getAjaxResult(mark,5004,"无法保存权限");
        return json;
    }

    /**
     * 获取permission数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPermissionForTree")
    public Object getPermissionForTree(){
        JSONArray jsonArray = (JSONArray) userService.getPermissionForTree();
        return jsonArray;
    }

    /**
     * 保存角色信息
     * 这是添加/修改/删除 角色的通用方法
     * @param role
     * @param describe
     * @param permissionList
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveRole")
    public Object saveRole(Role role, String [] permissionList){
//        if(role.getId().length()<32){
//            Role temp = new Role();
//            role.setId(temp.getId());
//        }在service里面进行了判断是否是新建的角色
        role.setNewdate(role.getDate());
        //初始化role结束。下面开始考虑如何保存
        //这里最关键的就是permission和role的关联数据，permission全部都在数据库中，所以只传了它的id
        //算了，先进入service吧
        String message = (String)userService.saveRole(role, permissionList);
        JSONObject ajaxResult = new JSONObject();
        if(message.equals("succ")){
             ajaxResult = baseService.getAjaxResult(true, -1, message);
        }else {
             ajaxResult = baseService.getAjaxResult(false, 5006, message);
        }
        return ajaxResult;
    }

    /**
     * roleManager页面删除单个角色按钮
     * @param role
     * @return
     */
    //@RequiresPermissions("role:deleteRole")
    @ResponseBody
    @RequestMapping("/deleteRole")
    public Object deleteRole(Role role){
        String mark = userService.deleteRole(role);
        return baseService.getAjaxResult(mark.equals("succ")?true:false,5007,mark);
    }

    @ResponseBody
    @RequestMapping("/getPermissionByRoleId")
    public Object getPermissionByRoleId(String roleid){
        List<Permission> permissionList = userService.getPermissionByRoleId(roleid);
        List<String> idList = new ArrayList<>();
        for(Permission p:permissionList){
            idList.add(p.getId());
        }
        return baseService.getAjaxResultHasObject(true,-1,"-1",idList);
    }

}
