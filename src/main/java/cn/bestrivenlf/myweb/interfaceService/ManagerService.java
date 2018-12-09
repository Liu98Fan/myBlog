package cn.bestrivenlf.myweb.interfaceService;

import cn.bestrivenlf.myweb.entity.*;

import java.util.List;

public interface ManagerService {
    public List<User> getAllUser();
    public int getUserCount();
    public List<User> getUserForPage(Page page);
    public boolean deleteUser(String id);
    public boolean saveMapping();
    public int getAuthorityCount();
    public List<UrlMapping> getAuthorityForTable(Page page);
    public List<ParentPermission> getParentAuthorityForTable(Page page);
    public List<Permission> getSonAuthorityForTable(String parent);
    public int getParentAuthorityCount();
    public boolean saveParentUrl(UrlMapping parentUrl);
}
