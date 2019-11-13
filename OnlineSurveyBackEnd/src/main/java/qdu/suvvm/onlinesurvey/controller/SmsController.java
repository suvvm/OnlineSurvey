package qdu.suvvm.onlinesurvey.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: SUVVM
 * @Date: 2019/11/13 19:17
 */
@RestController
public class SmsController {
    @PostMapping(value = "/getSms")
    public String test(@RequestParam("mobile") String mobile, @RequestParam("code") String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "yourAccessKid", "yourSecret");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", "suvvm");
        request.putQueryParameter("TemplateCode", "SMS_172602858");
        request.putQueryParameter("TemplateParam", code);
        try {
            CommonResponse response = client.getCommonResponse(request);
            Map map = (Map) JSON.parse(response.getData());
            if(map.get("Code").equals("OK")) {
                return "success";
            } else {
                return "error";
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
