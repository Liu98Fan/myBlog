package cn.bestrivenlf.myweb.interfaceDao;

import cn.bestrivenlf.myweb.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:LIUFAN
 * @date:2018/11/22
 */
public interface ManagerDao {
    @Select("select id,username,date,newdate,del_flag from  user_account_tb order by newdate DESC where del_flag=0 ")
    public List<User> getAllUser();
    @Select("select count(*) from user_account_tb where del_flag = 0;")
    public int getUserCount();
    @Select("select id,username,date,newdate,del_flag from user_account_tb where del_flag=0 order by newdate DESC  limit #{param1},#{param2}")
    public List<User> getUserForPage(int start, int end);
    @Select("call deleteUser(#{id})")
    public String deleteUser(String id);
}
