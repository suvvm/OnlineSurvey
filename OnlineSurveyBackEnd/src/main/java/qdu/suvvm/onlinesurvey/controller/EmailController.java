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
    public String sendEmail(@RequestParam("email") String email) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ak_id, ak_secret);
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        request.setAccountName("popsurvey@mail.suvvm.work");
        request.setFromAlias("SUVVM");
        request.setAddressType(1);
        request.setReplyToAddress(true);
        request.setToAddress(email);
        request.setSubject("PopSurvey审核失败");
        request.setHtmlBody("您有问卷审核失败，请检查是否合规，修改后重新提交！");
        try {
            SingleSendMailResponse response = client.getAcsResponse(request);
            // System.out.println(response);
            return response.toString();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
