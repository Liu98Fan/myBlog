package cn.bestrivenlf.myweb.interfaceService;

import cn.bestrivenlf.myweb.entity.Page;
import cn.bestrivenlf.myweb.entity.Role;
import cn.bestrivenlf.myweb.entity.User;

import java.util.List;

public interface UserService {
    public User getUserByUserName(String username);
    public boolean checkUsername(String username);
    public boolean register(User user);
    public List<Role> getRoleById(String id);
    public int getRoleCount();
    public List<Role> getAllRole();
    public List<Role> getRoleForPage(Page page);
    public List<User> getAllUser();
    public int getUserCount();
    public List<User> getUserForPage(Page page);
    public List<User> getUserForPage(Page page,String search);
    public boolean deleteUser(String id);
    public boolean bindRole(String userid,String roleid);
    public boolean reliveBindRole(String userid,String roleid);
}
