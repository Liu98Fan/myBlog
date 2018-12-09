package cn.bestrivenlf.myweb.interfaceService;

import net.sf.json.JSONObject;
import org.apache.shiro.util.ByteSource;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

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
    public Object Encrypt(String alogrithem, String password, ByteSource salt);
    public Object ConvertSaltByte(String salt);
    public JSONObject getAjaxResult(Boolean mark,int errorCode,String errorMessage);
    public JSONObject getAjaxResultHasObject(Boolean mark,int errorCode,String errorMessage,Object object);
    public Object getAllUrlMapping();
}
