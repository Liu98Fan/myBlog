package cn.bestrivenlf.myWeb.interfaceService;

import cn.bestrivenlf.myWeb.entity.Trace;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

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
