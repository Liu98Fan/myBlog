package cn.bestrivenlf.myWeb;

import cn.bestrivenlf.myWeb.entity.Note;
import cn.bestrivenlf.myWeb.interfaceDao.TraceDao;
import cn.bestrivenlf.myWeb.interfaceService.BaseService;

import cn.bestrivenlf.myWeb.interfaceService.NoteService;
import cn.bestrivenlf.myWeb.interfaceService.TraceService;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;
import cn.bestrivenlf.myWeb.entity.Trace;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMywebsiteApplicationTests {

    @Autowired
    RedisCacheManager redisCacheManager;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    BaseService baseService;
    @Autowired
    TraceService traceService;
    @Autowired
    NoteService noteService;
    @Autowired
    TraceDao traceDao;
    @Autowired
    JavaMailSenderImpl mailSender;


    @Test
    public void contextLoads() {
//        Cache cache = redisCacheManager.getCache("myset");
//        Note note = cache.get("object",);
//        System.out.println(note);
        redisTemplate.opsForValue().set("test1",new Note());
        Object object = redisTemplate.opsForValue().get("test1");
        System.out.println(object);
        Cache cache = redisCacheManager.getCache("myset");
        Object o1 = cache.get("object");
        System.out.println(o1);
        Object object1 = redisTemplate.opsForValue().get("object");
        System.out.println(object1);
    }
    @Test
    public void sendMessage(){
        Boolean b = (Boolean)baseService.sendMessageToEmptyCache("Note");
        if(b)
        System.out.println("success");
    }
    @Test
    public void sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("message from god！");
        message.setText("shagou,我又测试了唉~~~~~~~~~");
        message.setTo("88665291@qq.com");
        message.setFrom("214704958@qq.com");
        mailSender.send(message);
        System.out.println("succ");
    }
    @Test
    public void elasticSearch(){
        System.out.println("succ");
    }
    @Test
    public void testDate(){
        Date date = new Date();
        long time = date.getTime();
        int year= date.getYear();
        System.out.println(year);
        int year1 = Calendar.DAY_OF_YEAR;
        System.out.println(year1);
        Calendar now = Calendar.getInstance();
        System.out.println(now.get(Calendar.YEAR));
        System.out.println(now.get(Calendar.MONTH));
        System.out.println(now.get(Calendar.DAY_OF_MONTH));//DAY_OF_YEAR是一年中的第几天
    }
//    @Test
//    public void testTrace(){
//        List<Trace> traceList = traceService.getTrace(11);
//        System.out.println(traceList);
//    }
    @Test
    public void testJSON(){
        Note note = new Note();
        JSONObject jsonObject = JSONObject.fromObject(note);
        System.out.println(jsonObject.toString());
    }

//    public void test(){
//        Trace trace = traceService.getTraceById("4e0bab793b5f4cbd8db46eb0282c870f");
//        System.out.println(trace.getInformation());
//        System.out.println(JSONObject.fromObject(trace.getInformation()).get("content"));
//    }
//    @Test
//    public void insertData(){
//        List<Note> noteList = noteService.getAllNote();
//        for(Note note:noteList){
//            Trace trace = new Trace();
//            trace.setJsonInfor(JSONObject.fromObject(note));
//            trace.setKey("写了一篇笔记");
//            //System.out.println("写了一篇笔记");
//            trace.setNewdate(note.getDate());
//            trace.setAuthor( note.getAuthor());
//            trace.setDate(note.getDate());
//            trace.setInformation(JSONObject.fromObject(note).toString());
//            trace.setMark(1);
//            trace.setContent("发布了一篇笔记,标题为:"+ note.getTitle());
//            traceDao.saveTrace(trace);
//        }
//    }
}
