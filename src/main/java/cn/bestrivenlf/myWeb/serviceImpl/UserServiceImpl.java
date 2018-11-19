package cn.bestrivenlf.myWeb.serviceImpl;

import cn.bestrivenlf.myWeb.entity.Permission;
import cn.bestrivenlf.myWeb.entity.Role;
import cn.bestrivenlf.myWeb.entity.User;
import cn.bestrivenlf.myWeb.interfaceDao.UserDao;
import cn.bestrivenlf.myWeb.interfaceService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUserByUserName(String username) {
        User user = userDao.getUserByUserName(username);
        //进行权限装配
        List<Role> roleList = userDao.getRoleByUserId(user.getId());
        for(Role role:roleList){
            List<Permission> permissionList = userDao.getPerMissionByRoleId(role.getId());
            role.setPermissions(permissionList);
        }
        user.setRoles(roleList);
        return user;
    }
}
