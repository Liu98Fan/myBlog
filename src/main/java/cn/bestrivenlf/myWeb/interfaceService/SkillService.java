package cn.bestrivenlf.myWeb.interfaceService;

import cn.bestrivenlf.myWeb.entity.SkillParent;
import cn.bestrivenlf.myWeb.entity.SkillSon;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/10/29 16:54
 * @Description:
 */
public interface SkillService {
    public List<SkillParent> parseJson(String json);
    public int saveSkill(List<SkillParent> spList);
    @Cacheable(cacheNames = "Skill",key="#root.methodName")
    public List<SkillParent> getSkill()throws Exception;
}
