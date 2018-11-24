package cn.bestrivenlf.myweb.serviceImpl;

import cn.bestrivenlf.myweb.entity.Page;
import cn.bestrivenlf.myweb.entity.User;
import cn.bestrivenlf.myweb.interfaceDao.ManagerDao;
import cn.bestrivenlf.myweb.interfaceService.ManagerService;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:LIUFAN
 * @date:2018/11/22
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;
    @Override
    public List<User> getAllUser() {
        return  managerDao.getAllUser();
    }

    @Override
    public int getUserCount() {
        return managerDao.getUserCount();
    }

    @Override
    public List<User> getUserForPage(Page page) {
        int start = page.getCurrentResult();
        int end = start + page.getLimit();
        List<User> userList =  managerDao.getUserForPage(start,end);
        return userList;
    }

    @Override
    public boolean deleteUser(String id) {
        String mark = managerDao.deleteUser(id);
        if(mark.equals("succ")){
            return true;
        }else{
            return false;
        }

    }
}
