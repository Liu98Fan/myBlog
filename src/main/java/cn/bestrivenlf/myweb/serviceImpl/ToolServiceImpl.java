package cn.bestrivenlf.myweb.serviceImpl;

import cn.bestrivenlf.myweb.entity.LeaveMessage;
import cn.bestrivenlf.myweb.entity.TacModel;
import cn.bestrivenlf.myweb.interfaceDao.ToolDao;
import cn.bestrivenlf.myweb.interfaceService.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
