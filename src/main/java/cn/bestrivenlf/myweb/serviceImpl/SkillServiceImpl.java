package cn.bestrivenlf.myweb.serviceImpl;

import cn.bestrivenlf.myweb.entity.SkillParent;
import cn.bestrivenlf.myweb.entity.SkillSon;
import cn.bestrivenlf.myweb.interfaceDao.SkillDao;
import cn.bestrivenlf.myweb.interfaceService.SkillService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/10/29 16:54
 * @Description:
 */
@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillDao skillDao;
    @Override
    public List<SkillParent> parseJson(String jsonData) {
        JSONObject jsonObject = JSONObject.fromObject(jsonData);
        int length = Integer.parseInt((String)jsonObject.get("len"));
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        List<SkillParent> spList = new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jtemp = jsonArray.getJSONObject(i);
            SkillParent sp = (SkillParent) JSONObject.toBean(jtemp,SkillParent.class);
            JSONArray jtempArray = jtemp.getJSONArray("son");
            List<SkillSon> ssList = new ArrayList<>();
            for(int j =0;j<jtempArray.size();j++){
                JSONObject jtempson = jtempArray.getJSONObject(j);
                SkillSon ss = (SkillSon) JSONObject.toBean(jtempson,SkillSon.class);
                ss.setNewdate(ss.getDate());
                ssList.add(ss);
            }
            sp.setSon(ssList);
            sp.setNewdate(sp.getDate());
            spList.add(sp);
        }
        return spList;
    }

    @Override
    public int saveSkill(List<SkillParent> spList) {
        for(SkillParent sp:spList){
            //首先保存SkillP
            int su = skillDao.saveSkillP(sp);
            if(su==0){
                for(SkillSon ss:sp.getSon()){
                    ss.setPid(sp.getId());
                    int ssu = skillDao.saveSkillS(ss);
                    if(ssu>0){
                        return 1;
                    }
                }
            }else{
                return 1;
            }
        }
        return 0;
    }

    @Override
    public List<SkillParent> getSkill()throws Exception {
        List<SkillParent> splist = skillDao.getSkillP();
        if(splist!=null){
            for(SkillParent s:splist){
                String id = s.getId();
                List<SkillSon> sslist = skillDao.getSkillSonByPId(id);
                s.setSon(sslist);
            }
        }
        return splist;
    }

}
