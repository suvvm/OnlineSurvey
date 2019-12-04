package qdu.suvvm.onlinesurvey.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName: EmailUtil
 * @Description: 发送邮件
 * @Author: SUVVM
 * @Date: 2019/11/28 20:48
 */
public class EmailUtil {
    public boolean sendEmail(String email, String title, String body, String ak_id, String ak_secret) {
//        System.out.println("ak_id:" + ak_id);
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ak_id, ak_secret);
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        request.setAccountName("popsurvey@mail.suvvm.work");
        request.setFromAlias("SUVVM");
        request.setAddressType(1);
        request.setReplyToAddress(true);
        request.setToAddress(email);
        request.setSubject(title);
        request.setHtmlBody(body);
        try {
            SingleSendMailResponse response = client.getAcsResponse(request);
            // System.out.println(response);
            return true;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;

    }
}
