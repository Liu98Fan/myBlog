package cn.bestrivenlf.myweb.interfaceService;

import cn.bestrivenlf.myweb.entity.SkillParent;
import org.springframework.cache.annotation.Cacheable;

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
