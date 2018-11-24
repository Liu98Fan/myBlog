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
}
