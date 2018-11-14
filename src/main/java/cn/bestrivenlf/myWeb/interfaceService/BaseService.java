package cn.bestrivenlf.myWeb.interfaceService;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cache.annotation.CacheEvict;

/**
 * @Author: liufan
 * @Date: 2018/10/17 18:51
 * @Description:
 */
public interface BaseService {
    public Object saveRedisCache(String key,Object value);
    public Object sendMessageToEmptyCache(String key);
    @RabbitListener(queues = "cache.process.Note")
    public void clearCache(String s);
    public Object sendMail(String name,String mail,String message);
}
