package cn.bestrivenlf.myweb.interfaceDao;

import cn.bestrivenlf.myweb.entity.ParentPermission;
import cn.bestrivenlf.myweb.entity.Permission;
import cn.bestrivenlf.myweb.entity.Role;
import cn.bestrivenlf.myweb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from user_account_tb where username=#{username} and del_flag =0")
    public User getUserByUserName(String username);
    @Select("select b.id 'id',b.role 'role',b.describe 'describe',b.date 'date',b.newdate 'newdate',b.del_flag 'del_flag' from user_role_tb a join role_tb b on a.roleid = b.id where a.userid = #{userid} and a.del_flag = 0")
    public List<Role> getRoleByUserId(String userid);
    @Select("select b.id 'id',b.permission 'permission',b.describe 'describe',b.date 'date',b.newdate 'newdate',b.del_flag 'del_flag' from role_permission_tb a join permission_tb b on a.permissionid = b.id where a.roleid = #{roleid} and a.del_flag = 0")
    public List<Permission> getPerMissionByRoleId(String roleid);
    @Select("select * from user_account_tb where id = #{userid} and del_flag=0")
    public User getUserByUserId(String userid);
    @Select("call saveUser(#{id},#{username},#{password},#{salt},#{date},#{newdate},#{del_flag})")
    public String register(User user);
    @Select("select count(*) from role_tb where del_flag=0")
    public int getRoleCount();
    @Select("select id,role,`describe`,date,newdate,del_flag from role_tb where del_flag = 0")
    public List<Role> getAllRole();
    @Select("select id,role,`describe`,date,newdate,del_flag from role_tb where del_flag = 0 order by date DESC limit" +
            " #{param1},#{param2}")
    public List<Role> getRoleForPage(int start, int end);
    @Select("select id,username,date,newdate,del_flag from  user_account_tb order by newdate DESC where del_flag=0 ")
    public List<User> getAllUser();
    @Select("select count(*) from user_account_tb where del_flag = 0;")
    public int getUserCount();
    @Select("select id,username,date,newdate,del_flag from user_account_tb where del_flag=0 order by newdate DESC  limit #{param1},#{param2}")
    public List<User> getUserForPage(int start, int end);
    @Select("call deleteUser(#{id})")
    public String deleteUser(String id);
    @Select("select id,username,date,newdate,del_flag from user_account_tb where del_flag=0 and username like " +
            "'%#{search}%'order by newdate DESC  limit #{start},#{end}")
    public List<User> getUserForPageHasSearch(@Param("start") int start, @Param("end") int end, @Param("search") String
            search);
    @Select("call bindRole(#{param1},#{param2},0) ")
    public String bindRole(String userid,String roleid);
    @Select("call bindRole(#{param1},#{param2},1)")
    public String reliveBindRole(String userid,String roleid);
    @Select("call savePermission(#{id},#{permission},#{describe},#{date},#{newdate},#{del_flag},#{parentId},#{parent})")
    public String savePermission(Permission permission);
    @Select("update parent_permission_tb set `describe` = #{parentDescribe},del_flag = #{del_flag} where id = #{parentId}")
    public String saveParentPermission(ParentPermission parentPermission);
    @Select("select \n" +
            "a.id as 'id',\n" +
            "a.permission as 'permission',\n" +
            "a.`describe` as 'describe',\n" +
            "a.date as 'date',\n" +
            "a.newdate as 'newdate',\n" +
            "c.id as 'parentid',\n" +
            "c.parent as 'parent',\n" +
            "c.`describe` as 'parentdescribe',\n" +
            "a.del_flag as'del_flag' \n" +
            " from permission_tb a \n" +
            "LEFT JOIN parent_permission_relation_tb b on a.id = b.permissionid \n" +
            "LEFT JOIN parent_permission_tb c on b.parentid = c.id  ")
    public List<Permission>getPermissionForTree();
    @Select("call saveRole(#{id},#{role},#{describe},#{date},#{newdate},#{del_flag})")
    public String saveRole(Role role);
    @Select("call insertPermissionToRole(#{param1},#{param2})")
    public String insertPermissionToRole(String id, String permission);
    @Select("select * from role_tb where id = #{id} and del_flag = 0")
    public Role getRoleById(String id);
    @Update("update role_permission_tb set del_flag = 1 where roleid=#{param1} and permissionid=#{param2}")
    public void deleteRolePermission(String id, String pid);
    @Select("select count(*) from role_tb where role = #{role}")
    public int checkRole(String role);
}
