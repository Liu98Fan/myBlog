package cn.bestrivenlf.myweb;

import cn.bestrivenlf.myweb.entity.Note;
import cn.bestrivenlf.myweb.entity.Permission;
import cn.bestrivenlf.myweb.interfaceDao.TraceDao;
import cn.bestrivenlf.myweb.interfaceDao.UserDao;
import cn.bestrivenlf.myweb.interfaceService.BaseService;

import cn.bestrivenlf.myweb.interfaceService.NoteService;
import cn.bestrivenlf.myweb.interfaceService.TraceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

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
    UserDao userDao;
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    WebApplicationContext webApplicationContext;


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
        message.setText("shagou,我又发布新版本了，欢迎访问www.bestrivenlf.cn进行最新体验，新出了留言娱乐功能，快来给我留言吧~~~~~~~~~");
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
    @Test
    public void testEncrypt(){
        Object salt = baseService.ConvertSaltByte("admin1");
        Object encrypt = baseService.Encrypt("MD5", "admin", (ByteSource) salt);
        Object en2 =new SimpleHash("MD5","admin");
        System.out.println(encrypt.toString());
        System.out.println(en2.toString());
    }
    @Test
    public void getAllUrl(){
        JSONArray jsonArray = (JSONArray) baseService.getAllUrlMapping();
        System.out.println(jsonArray.size());
    }
    @Test
    public void testSplit(){
        String url = "/center/ta";
        System.out.println(url.split("/")[2]);
    }
    @Test
    public void testTree(){
        List<Permission> permissionList = userDao.getPermissionForTree();
        //进行删选组装
       JSONArray jsonArray = new JSONArray();
        while(permissionList.size()>0){
            JSONObject json = new JSONObject();
            Permission parent = permissionList.get(0);
            json.put("parent",parent);
            List<Permission> permissions = new ArrayList<>();
            List<Integer> indexs = new ArrayList<>();
            for(int i = 0;i<permissionList.size();i++){
                if(permissionList.get(i).getParent().equals(parent.getParent())){
                    indexs.add(i);
                }
            }
            //剔除
            for(int j = indexs.size()-1;j>=0;j--){
                permissions.add(permissionList.get(indexs.get(j)));
                permissionList.remove((int)indexs.get(j));
            }
            //加入json
            json.put("son",permissions);
            //加入json数组
            jsonArray.add(json);
        }
        System.out.println(jsonArray.toString());
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
