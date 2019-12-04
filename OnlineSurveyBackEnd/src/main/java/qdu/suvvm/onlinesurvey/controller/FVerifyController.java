package qdu.suvvm.onlinesurvey.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.mapper.UserMapper;
import qdu.suvvm.onlinesurvey.pojo.User;
import qdu.suvvm.onlinesurvey.utils.FVerifyUtil;

import java.util.List;

/**
 * @ClassName: FVerifyController
 * @Description: 处理人脸识别的相关请求
 * @Author: SUVVM
 * @Date: 2019/11/18 22:06
 */
@RestController
public class FVerifyController {
    @Autowired
    private UserMapper userMapper;

    @Value("${privateInfo.accessKeyId}")
    private String ak_id;    // 阿里ak_id配置在application-privateInfo中，由于是敏感数据并未上传

    @Value("${privateInfo.accessKeySecret}")
    private String ak_secret;    // 阿里ak_secret配置在application-privateInfo中，由于是敏感数据并未上传

    /**
     * @FunctionName: FVerify
     * @Description: 处理人脸识别的请求
     * @Parameter:
     *  pnum 客户机请求中的手机号参数
     *  content_1 客户机请求中的图片base64编码
     * @Return: 无法查找到用户返回"null",识别成功返回将对应的识别信息与数据库对应用户信息构成JSON串返回,失败返回字符串"error"
     */
    @PostMapping(value = "/fVerify")
    public String FVerify(@RequestParam("pnum") String pnum, @RequestParam("content_1") String content_1) {
        int type = 1;
        String url = "https://dtplus-cn-shanghai.data.aliyuncs.com/face/verify";
        User user = new User();
        user.setPnum(pnum); // 将传入数据作为手机号查询用户
        List<User> users = userMapper.getUser(user);
        if (users.isEmpty()) {
            user.setPnum(null);
            user.setUsername(pnum); // 将传入的数据作为用户名查询数据
            users = userMapper.getUser(user);
            if (users.isEmpty()) {
                user.setUsername(null);
                user.setEmail(pnum);    // 将传入的数据作为用户邮箱进行查询
                users = userMapper.getUser(user);
                if (users.isEmpty()) {
                    return "null";
                }
            }
        }

        String content_2 = users.get(0).getImgbase64();
        String body = "{\"type\":\""+type+"\",\"content_1\":\""+content_1+"\",\"content_2\":\""+content_2+"\"}";
        String res = null;
        try {
            res = FVerifyUtil.sendPost(url, body, ak_id, ak_secret);
            System.out.println(res);
            JSONObject resObj = JSON.parseObject(res);
            resObj.put("id", users.get(0).getId());
            resObj.put("username", users.get(0).getUsername());
            resObj.put("name", users.get(0).getName());
            resObj.put("pnum", users.get(0).getPnum());
            resObj.put("email", users.get(0).getEmail());
            resObj.put("gender", users.get(0).getGender());
            // resObj.put("avatar", users.get(0).getAvatar());
            resObj.put("power", users.get(0).getPower());
            res = JSON.toJSONString(resObj);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }
}
