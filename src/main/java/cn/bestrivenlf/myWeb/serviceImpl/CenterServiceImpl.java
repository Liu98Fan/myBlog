package cn.bestrivenlf.myWeb.serviceImpl;


import cn.bestrivenlf.myWeb.entity.TacModel;
import cn.bestrivenlf.myWeb.interfaceDao.CenterDao;
import cn.bestrivenlf.myWeb.interfaceService.CenterService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Author: liufan
 * @Date: 2018/8/30 09:13
 * @Description: implement for center service interface
 */
@Service
public class CenterServiceImpl implements CenterService {
    @Autowired
    private CenterDao centerDao;


    @Override

    public void saveTacModel(ArrayList<TacModel> tacModelList)throws Exception {
        for(TacModel t:tacModelList){
            if(t.getId().equals("0")){
                //出错 id不可以为0
                throw new Exception("IdNotZero");
            }else{
                centerDao.saveTacModel(t);
            }
        }
    }

    @Override
    public JSONArray getDescription(int mark) {
        ArrayList<TacModel> tacModelList = centerDao.getDescription(mark);
        JSONArray jsonArray = JSONArray.fromObject( tacModelList);
        return jsonArray;
    }
}
