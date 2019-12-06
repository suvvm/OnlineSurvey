package qdu.suvvm.onlinesurvey.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DruidConfig
 * @Description: druid配置类
 * @Author: SUVVM
 * @Date: 2019/10/31 21:58
 */
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }
    /**
     * @FunctionName: statViewServlet
     * @Description: 配置监控服务器
     * @Return 返回监控注册的servlet对象
     */
    @Bean
    @SuppressWarnings("unchecked")
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername","root"); // 登录用户名
        initParams.put("loginPassword", "root");    // 登录密码
        initParams.put("allow",""); // 白名单（默认所有）

        servletRegistrationBean.setInitParameters(initParams);

        return servletRegistrationBean;
    }
    /**
     * @FunctionName: webStatFilter
     * @Description: 配置过滤器
     * @Return 返回过滤器配置对象
     */
    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        // 忽略格式
        initParams.put("exclusions", "*.js, *.css, *.png, *.ico, /druid/");

        registrationBean.setInitParameters(initParams);
        // 过滤规则
        registrationBean.setUrlPatterns(Arrays.asList("/"));

        return registrationBean;
    }
}
