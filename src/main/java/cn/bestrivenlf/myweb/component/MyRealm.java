package cn.bestrivenlf.myweb.component;

import cn.bestrivenlf.myweb.entity.Role;
import cn.bestrivenlf.myweb.entity.User;
import cn.bestrivenlf.myweb.interfaceService.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        Set<Role> roles = user.getRoleConvertSet();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(user.getPermissionCovertStringSet());
        info.setRoles(user.getRoleConvertStringSet());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.getUserByUserName(username);
        if(user!=null){
            //查到相关信息
            Object principal = user;
            //这里的密码是从数据库查询出来的正确的密码
            String credential = user.getPassword();
            String realmName = super.getName();
            // public SimpleAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName)
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credential,realmName);
            return info;
        }else{
            throw new UnknownAccountException("账号不存在");
        }
    }
}
