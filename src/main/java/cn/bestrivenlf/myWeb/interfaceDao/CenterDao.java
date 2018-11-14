package cn.bestrivenlf.myWeb.interfaceDao;

import cn.bestrivenlf.myWeb.entity.TacModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @Author: liufan
 * @Date: 2018/8/30 11:00
 * @Description:
 */
@Mapper
public interface CenterDao {
    @Select("CALL saveTacModel(#{id},#{mark},#{title},#{content},#{date},#{del_flag});")
    public void saveTacModel(TacModel t);
    @Select("select * from center_tb where mark = #{mark} and del_flag = 0;")
    public ArrayList<TacModel> getDescription(int mark);
}
