package cn.bestrivenlf.myweb.interfaceService;

import cn.bestrivenlf.myweb.entity.FileEntity;
import cn.bestrivenlf.myweb.entity.LeaveMessage;
import cn.bestrivenlf.myweb.entity.TacModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author:LIUFAN
 * @date:2018/11/22
 */
public interface ToolService {
    public void saveMessage(LeaveMessage message);
    public List<LeaveMessage> getMessage();
    public String getMd5(MultipartFile upload) throws Exception;
    public Boolean saveFile(List<FileEntity> fileEntities);
}
