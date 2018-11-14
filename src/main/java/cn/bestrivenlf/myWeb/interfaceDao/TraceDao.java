package cn.bestrivenlf.myWeb.interfaceDao;

import cn.bestrivenlf.myWeb.entity.Trace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.messaging.simp.annotation.SendToUser;

import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/11/10 18:40
 * @Description:
 */
@Mapper
public interface TraceDao {
    @Select("insert into trace_tb values(#{id},#{content},#{author},#{mark},#{information},#{newdate},#{date}," +
            "#{del_flag})")
    public Object saveTrace(Trace trace);
    //public Trace getTrace(Integer Month);
    @Select("select * from trace_tb where del_flag = 0")
    public List<Trace> getAllTrace();
    @Select("select * from trace_tb where del_flag = 0 and id = #{id}")
    public Trace getTraceById(String id);
    @Select("update trace_tb set del_flag = 1 where id = #{id}")
    public Object deleteTrace(String id);
    @Select("select *   from trace_tb where del_flag = 0 order by newdate DESC limit #{count}  ")
    //@Cacheable(cacheNames = "Trace",key = "#root.methodName")
    public List<Trace> getTraceByCount(int count);
}
