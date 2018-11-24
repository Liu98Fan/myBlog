package cn.bestrivenlf.myweb.interfaceDao;

import cn.bestrivenlf.myweb.entity.LeaveMessage;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ToolDao {
    @Select("insert into leaveMessage_tb values(#{id},#{content},#{userid},#{date},#{del_flag})")
    public void saveMessage(LeaveMessage message);
    @Select("select * from leaveMessage_tb where del_flag = 0")
    public List<LeaveMessage> getMessage();
}
