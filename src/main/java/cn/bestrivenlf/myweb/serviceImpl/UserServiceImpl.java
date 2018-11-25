package cn.bestrivenlf.myweb.serviceImpl;

import cn.bestrivenlf.myweb.entity.Page;
import cn.bestrivenlf.myweb.entity.Permission;
import cn.bestrivenlf.myweb.entity.Role;
import cn.bestrivenlf.myweb.entity.User;
import cn.bestrivenlf.myweb.interfaceDao.UserDao;
import cn.bestrivenlf.myweb.interfaceService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUserByUserName(String username) {
       try{
           User user = userDao.getUserByUserName(username);
           //进行权限装配
           List<Role> roleList = userDao.getRoleByUserId(user.getId());
           for(Role role:roleList){
               List<Permission> permissionList = userDao.getPerMissionByRoleId(role.getId());
               role.setPermissions(permissionList);
           }
           user.setRoles(roleList);
           return user;
       }catch (NullPointerException e){
           return null;
       }
    }

    @Override
    public boolean checkUsername(String username) {
        return getUserByUserName(username)==null?true:false;
    }

    @Override
    public boolean register(User user) {
        String backValue = userDao.register(user);
        if(backValue.equals("succ")) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<Role> getRoleById(String id) {
        return userDao.getRoleByUserId(id);
    }

    @Override
    public int getRoleCount() {
        return userDao.getRoleCount();
    }

    @Override
    public List<Role> getAllRole() {
        return userDao.getAllRole();
    }



    @Override
    public List<Role> getRoleForPage(Page page) {
        int start = page.getCurrentResult();
        int end = start + page.getLimit();
        List<Role> roleList =  userDao.getRoleForPage(start,end);
        return roleList;
    }
    @Override
    public List<User> getAllUser() {
        return  userDao.getAllUser();
    }

    @Override
    public int getUserCount() {
        return userDao.getUserCount();
    }

    @Override
    public List<User> getUserForPage(Page page) {
        int start = page.getCurrentResult();
        int end = start + page.getLimit();
        List<User> userList =  userDao.getUserForPage(start,end);
        return userList;
    }

    @Override
    public List<User> getUserForPage(Page page, String search) {
        int start = page.getCurrentResult();
        int end = start + page.getLimit();
//        Map<Object,Object> paramMap = new HashMap<>();
//        paramMap.put("start",start);
//        paramMap.put("end",end);
//        paramMap.put("search",search);
        List<User> userList =  userDao.getUserForPageHasSearch(start,end,search);
        return userList;
    }

    @Override
    public boolean deleteUser(String id) {
        String mark = userDao.deleteUser(id);
        if(mark.equals("succ")){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean bindRole(String userid, String roleid) {
        String s = userDao.bindRole(userid, roleid);
        if(s.equals("succ")){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean reliveBindRole(String userid, String roleid) {
        String s = userDao.reliveBindRole(userid, roleid);
        if(s.equals("succ")){
            return true;
        }else{
            return false;
        }
    }

}
