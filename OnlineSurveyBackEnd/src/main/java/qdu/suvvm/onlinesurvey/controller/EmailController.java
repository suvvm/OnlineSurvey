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
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2019/11/26 21:20
 */
@RestController
public class EmailController {
    @Value("${privateInfo.accessKeyId}")
    private String ak_id;

    @Value("${privateInfo.accessKeySecret}")
    private String ak_secret;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam("email") String email, @RequestParam("title") String title, @RequestParam("body") String body) {
        EmailUtil emailUtil = new EmailUtil();
        if(emailUtil.sendEmail(email, title, body, ak_id, ak_secret)) {
            return "success";
        }
        return "error";
    }
}
