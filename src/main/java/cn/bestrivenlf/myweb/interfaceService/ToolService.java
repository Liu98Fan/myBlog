package cn.bestrivenlf.myweb.interfaceService;

import cn.bestrivenlf.myweb.entity.LeaveMessage;
import cn.bestrivenlf.myweb.entity.TacModel;

import java.util.List;

/**
 * @author:LIUFAN
 * @date:2018/11/22
 */
public interface ToolService {
    public void saveMessage(LeaveMessage message);
    public List<LeaveMessage> getMessage();
}
