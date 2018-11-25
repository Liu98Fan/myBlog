package cn.bestrivenlf.myweb.serviceImpl;

import cn.bestrivenlf.myweb.interfaceService.BaseService;
import cn.bestrivenlf.myweb.interfaceService.NoteService;
import net.sf.json.JSONObject;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: liufan
 * @Date: 2018/10/17 18:53
 * @Description:
 */
@Service
public class BaseSeriveImpl implements BaseService {
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private NoteService noteService;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Object saveRedisCache(String key, Object value) {
        try{
            redisTemplate.opsForValue().set(key,value,10,TimeUnit.MINUTES);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }

    }

    @Override
    public Object sendMessageToEmptyCache(String key) {
        try{
            rabbitTemplate.convertAndSend("exchange.fanount","cache.clear",key);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public void clearCache(String s) {
        try{
            if(s.equals("Note")) {
                noteService.clearCache();
            }

        }catch (Exception e){
            logger.error(e.getMessage());

        }


    }

    /**
     * send mail to myself (index.html)
     * @param name sender name
     * @param mail sender mail address
     * @param message sender message
     * @return if success or fail
     */
    @Override
    public Object sendMail(String name, String mail, String message) {
        try{
            SimpleMailMessage m = new SimpleMailMessage();
            m.setFrom("214704958@qq.com");
            m.setSubject("www.bestriven.cn");
            m.setTo("214704958@qq.com");
            m.setText("来自 "+name+"("+mail+") "+message);
            javaMailSender.send(m);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }

    }

    @Override
    public Object Encrypt(String alogrithem, String password, ByteSource salt) {
        Object result = new SimpleHash(alogrithem,password,salt);
        return result;
    }
    @Override
    public Object ConvertSaltByte(String salt){
        ByteSource saltByte = ByteSource.Util.bytes(salt);
        return saltByte;
    }

    @Override
    public JSONObject getAjaxResult(Boolean mark,int errorCode,String errorMessage) {
        JSONObject json = new JSONObject();
        String success = "success";
        String message = null;
        int code = 200;
        if(!mark){
            success="fail";
            code = errorCode;
            message = errorMessage;
        }
        json.put("success",success);
        json.put("code",code);
        json.put("message",message);
        return json;
    }

    @Override
    public JSONObject getAjaxResultHasObject(Boolean mark, int errorCode, String errorMessage, Object object) {
        JSONObject json = new JSONObject();
        String success = "success";
        String message = null;
        Object obj = object;
        int code = 200;
        if(!mark){
            success="fail";
            code = errorCode;
            message = errorMessage;
        }
        json.put("success",success);
        json.put("code",code);
        json.put("message",message);
        json.put("object",obj);
        return json;

    }
}
