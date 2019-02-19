package cn.bestrivenlf.myweb.config;

import cn.bestrivenlf.myweb.component.MyLocaleResolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: liufan
 * @Date: 2018/10/1 11:18
 * @Description:
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Value("${file.accessPath}")
    String accessPath;
    @Value("${file.uploadPath}")
    String uploadPath;
    @Value("${file.noteResourceUploadPath}")
    String noteResourceUploadPath;
    @Value("${file.noteAccessPath}")
    String noteAccessPath;
    @Value("${file.musicPath}")
    String musicPath;
    @Value("${file.musicAccessPath}")
    String musicAcessPath;
    /**
     * 首页显示
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/main.html").setViewName("redirect:/main/entrance");
                registry.addViewController("/").setViewName("welcome.html");
                registry.addViewController("/firstPage").setViewName("redirect:/");
//                registry.addViewController("index").setViewName("redirect:/center/entrance");
            }
            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {

                //registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/index.html","/index","/");
            }

        };
        return configurer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(accessPath+"**").addResourceLocations("file:"+uploadPath);
        registry.addResourceHandler(musicAcessPath+"**").addResourceLocations("file:"+musicPath);
        registry.addResourceHandler(noteAccessPath+"**").addResourceLocations("file:"+noteResourceUploadPath);
    }

    /**
     * 国际化
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
