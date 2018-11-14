package cn.bestrivenlf.myWeb.interfaceDao;

import cn.bestrivenlf.myWeb.entity.SkillParent;
import cn.bestrivenlf.myWeb.entity.SkillSon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/11/5 13:19
 * @Description:
 */
@Mapper
public interface SkillDao {
    public Object saveSkill(List<SkillParent> spList);
    public int saveSkillP(SkillParent sp);
    public int saveSkillS(SkillSon ss);
    public List<SkillParent> getSkillP();
    public List<SkillSon> getSkillSonByPId(String pid);
    public int getSkillPNum();
    public int getAllSkillSonNum();
}
