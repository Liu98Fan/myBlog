package cn.bestrivenlf.myweb.interfaceService;

import cn.bestrivenlf.myweb.entity.Page;
import cn.bestrivenlf.myweb.entity.User;

import java.util.List;

public interface ManagerService {
    public List<User> getAllUser();
    public int getUserCount();
    public List<User> getUserForPage(Page page);
    public boolean deleteUser(String id);
}
