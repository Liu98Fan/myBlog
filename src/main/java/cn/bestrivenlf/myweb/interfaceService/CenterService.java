package cn.bestrivenlf.myweb.interfaceService;


import cn.bestrivenlf.myweb.entity.TacModel;
import net.sf.json.JSONArray;

import java.util.ArrayList;

/**
 * @Author: liufan
 * @Date: 2018/8/30 09:11
 * @Description: center service interface
 */
public interface CenterService {
    public void saveTacModel(ArrayList<TacModel> tacModelList) throws  Exception;
    public JSONArray getDescription(int mark);
}
