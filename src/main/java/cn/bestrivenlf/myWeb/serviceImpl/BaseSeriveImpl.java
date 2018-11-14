package cn.bestrivenlf.myWeb.serviceImpl;

import cn.bestrivenlf.myWeb.interfaceService.BaseService;
import cn.bestrivenlf.myWeb.interfaceService.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
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
            if(s.equals("Note"))
                noteService.clearCache();

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
}
