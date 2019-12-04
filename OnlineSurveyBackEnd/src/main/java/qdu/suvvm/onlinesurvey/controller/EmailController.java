package qdu.suvvm.onlinesurvey.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.utils.EmailUtil;

/**
 * @ClassName: EmailController
 * @Description: 邮件发送Controller
 * @Author: SUVVM
 * @Date: 2019/11/26 21:20
 */
@RestController
public class EmailController {
    @Value("${privateInfo.accessKeyId}")
    private String ak_id;   // 阿里ak_id配置在application-privateInfo中，由于是敏感数据并未上传

    @Value("${privateInfo.accessKeySecret}")
    private String ak_secret;   // 阿里ak_secret配置在application-privateInfo中，由于是敏感数据并未上传

    /**
     * @FunctionName: sendEmail
     * @Description: 处理发送邮件请求
     * @Parameter:
     *  email 客户机请求中要接收邮件的邮箱地址
     *  title 客户机请求中邮件标题
     *  body 客户机请求中邮件内容
     * @Return: 成功返回"success"，失败返回"error"
     */
    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam("email") String email, @RequestParam("title") String title, @RequestParam("body") String body) {
        EmailUtil emailUtil = new EmailUtil();
        if(emailUtil.sendEmail(email, title, body, ak_id, ak_secret)) { // 发送邮件
            return "success";
        }
        return "error";
    }
}
