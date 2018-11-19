package cn.bestrivenlf.myWeb.interfaceDao;

import cn.bestrivenlf.myWeb.entity.Permission;
import cn.bestrivenlf.myWeb.entity.Role;
import cn.bestrivenlf.myWeb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from user_account_tb where username=#{username} and del_flag =0")
    public User getUserByUserName(String username);
    @Select("select b.id 'id',b.role 'role',b.describe 'describe',b.date 'date',b.newdate 'newdate',b.del_flag 'del_flag' from user_role_tb a join role_tb b on a.roleid = b.id where a.userid = #{userid} and del_flag = 0")
    public List<Role> getRoleByUserId(String userid);
    @Select("select b.id 'id',b.permission 'permission',b.describe 'describe',b.date 'date',b.newdate 'newdate',b.del_flag 'del_flag' from role_permission_tb a join permission_tb b on a.permissionid = b.id where a.roleid = #{roleid} and del_flag = 0")
    public List<Permission> getPerMissionByRoleId(String roleid);
    @Select("select * from user_account_tb where id = #{userid} and del_flag=0")
    public User getUserByUserId(String userid);
}
