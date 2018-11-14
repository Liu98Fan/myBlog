package cn.bestrivenlf.myWeb.serviceImpl;

import cn.bestrivenlf.myWeb.entity.Note;
import cn.bestrivenlf.myWeb.interfaceDao.TraceDao;
import cn.bestrivenlf.myWeb.interfaceService.TraceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import cn.bestrivenlf.myWeb.entity.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/11/10 12:57
 * @Description:
 */
@Service
@Aspect
public class TraceServiceImpl implements TraceService {
    @Autowired
    private TraceDao traceDao;
    @Override
    @After(value = "execution(* cn.bestrivenlf.myWeb.controller.NoteController.saveNote(..))")
    public Object getTarget(JoinPoint joinPoint) {
        Object [] paramList = joinPoint.getArgs();
        cn.bestrivenlf.myWeb.entity.Trace trace = new cn.bestrivenlf.myWeb.entity.Trace();
        if(paramList.length==1){
            Object obj = paramList[0];
            if(obj instanceof Note){
                trace.setKey("写了一篇笔记");
                //System.out.println("写了一篇笔记");
                trace.setNewdate(trace.getDate());
                trace.setAuthor(((Note) obj).getAuthor());
                JSONObject jsonObject = JSONObject.fromObject((Note)obj);
                String infor = jsonObject.toString();
                trace.setInformation(infor);
                trace.setMark(1);
                trace.setContent("发布了一篇笔记,标题为:"+((Note) obj).getTitle());
                traceDao.saveTrace(trace);
            }
        }else{
            System.out.println("参数长度："+paramList.length);
        }
        return trace;
    }

    @Override
    public List<Trace> getTrace(Integer Month) {
        List<cn.bestrivenlf.myWeb.entity.Trace> traceList = traceDao.getAllTrace();
        for(cn.bestrivenlf.myWeb.entity.Trace trace:traceList){
            String date = trace.getNewdate();
            String month = date.split("-")[1];
            try {
                if (Integer.parseInt(month) != Month)
                    traceList.remove(trace);
            }catch (NumberFormatException e){
                traceList.remove(trace);
            }
        }
        return traceList;
    }

    @Override
    public List<cn.bestrivenlf.myWeb.entity.Trace> getAllTrace() {
        return traceDao.getAllTrace();
    }

    @Override
    public cn.bestrivenlf.myWeb.entity.Trace getTraceById(String id) {
        return traceDao.getTraceById(id);
    }

    @Override
    public Object deleteTrace(String id) {
        traceDao.deleteTrace(id);
        return true;
    }

    @Override
    public JSONObject getTraceByCount(int count) {
        ArrayList<Trace> traceList = (ArrayList<Trace>)traceDao.getTraceByCount(count);
        JSONObject jsonObject = new JSONObject();
        for(Trace trace:traceList){
            trace.setJsonInfor(JSONObject.fromObject(trace.getInformation()));
            String date = trace.getNewdate();
            String ym = date.split("-")[0].concat(date.split("-")[1]);//201808
            if(jsonObject.get(ym)==null) {
                ArrayList<Trace> traces = new ArrayList<Trace>();
                traces.add(trace);
                jsonObject.put(ym, traces);
            }
            else {
                JSONArray jsonArray = (JSONArray) jsonObject.get(ym);
                jsonArray.add(trace);
            }

        }
        return jsonObject;
    }
}
