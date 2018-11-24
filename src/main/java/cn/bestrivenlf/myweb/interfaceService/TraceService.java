package cn.bestrivenlf.myweb.interfaceService;

import cn.bestrivenlf.myweb.entity.Trace;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/11/10 12:44
 * @Description:日迹功能
 */

public interface TraceService {
    @CacheEvict(cacheNames = "Trace",allEntries = true)
    public Object getTarget(JoinPoint joinPoint);
    public List<Trace> getTrace(Integer Month);
    public List<Trace> getAllTrace();
    public Trace getTraceById(String id);
    @CacheEvict(cacheNames = "Trace",allEntries = true)
    public Object deleteTrace(String id);

    public JSONObject getTraceByCount(int count);
}
