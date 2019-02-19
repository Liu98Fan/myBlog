package cn.bestrivenlf.myweb.serviceImpl;

import cn.bestrivenlf.myweb.entity.FileEntity;
import cn.bestrivenlf.myweb.entity.LeaveMessage;
import cn.bestrivenlf.myweb.entity.TacModel;
import cn.bestrivenlf.myweb.interfaceDao.ToolDao;
import cn.bestrivenlf.myweb.interfaceService.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

/**
 * @author:LIUFAN
 * @date:2018/11/22
 */
@Service
public class ToolServiceImpl implements ToolService {
    @Autowired
    private ToolDao toolDao;
    @Override
    public void saveMessage(LeaveMessage message) {
        toolDao.saveMessage(message);
    }

    @Override
    public List<LeaveMessage> getMessage() {
        return toolDao.getMessage();
    }

    @Override
    public String getMd5(MultipartFile upload) throws Exception {
        byte[] uploadBytes = upload.getBytes();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(uploadBytes);
        String hashString = new BigInteger(1, digest).toString(16);
        return hashString.toUpperCase();
    }

    @Override
    public Boolean saveFile(List<FileEntity> fileEntities) {
        for(FileEntity file:fileEntities){
            InputStream inputStream = file.getFileStream();
            OutputStream outputStream = null;
            if(null!=inputStream){
               try{
                   //1、创建空文件
                   File tempFile = new File(file.getAbsolutePath());
                   if(!tempFile.exists()){
                       tempFile.createNewFile();
                   }
                   //2、获取输出流
                   outputStream = new FileOutputStream(tempFile);
                   //3、字节流转byte
                   byte[] temp = new byte[1024];
                   int length = 0;
                   while ((length = inputStream.read(temp)) != -1) {
                       // 目标文件写入一部分内容
                       outputStream.write(temp, 0, length);
                   }
               }catch (Exception e){
                   e.printStackTrace();
                   return false;
               }finally {
                   try {
                       outputStream.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                       return false;
                   }
               }

            }
        }
        return true;
    }

}
