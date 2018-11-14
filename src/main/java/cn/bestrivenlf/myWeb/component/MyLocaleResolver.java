package cn.bestrivenlf.myWeb.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Author: liufan
 * @Date: 2018/10/1 12:22
 * @Description: 可以在连接上携带上区域信息
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String name = httpServletRequest.getParameter("l");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(name)){
            String [] split = name.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
