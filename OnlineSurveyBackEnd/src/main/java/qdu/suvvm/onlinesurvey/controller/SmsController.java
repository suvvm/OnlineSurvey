package qdu.suvvm.onlinesurvey.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName: SmsController
 * @Description: Controller层 实现接收前端数据调用阿里对应短信服务
 * @Author: SUVVM
 * @Date: 2019/11/13 19:17
 */
@RestController
public class SmsController {

    @Value("${privateInfo.accessKeyId}")
    private String ak_id;

    @Value("${privateInfo.accessKeySecret}")
    private String ak_secret;

    /**
     * @FunctionName: getSms
     * @Description: 处理发送验证码请求
     * @Parameter:
     *  mobile 客户机请求参数中的手机号信息
     *  password 客户机请求参数中的验证码信息
     * @Return: 发送成功返回字符串"success",失败返回字符串"error"
     */
    @PostMapping(value = "/getSms")
    public String getSms(@RequestParam("mobile") String mobile, @RequestParam("code") String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ak_id, ak_secret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
//        request.setMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
//        request.setDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
//        request.setVersion("2017-05-25");
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
