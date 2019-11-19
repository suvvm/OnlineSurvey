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
    private String ak_id;

    @Value("${privateInfo.accessKeySecret}")
    private String ak_secret;

    @PostMapping(value = "/fVerify")
    public String FVerify(@RequestParam("pnum") String pnum, @RequestParam("content_1") String content_1) {
        int type = 1;
        String url = "https://dtplus-cn-shanghai.data.aliyuncs.com/face/verify";
        User user = new User();
        user.setPnum(pnum);
        List<User> users = userMapper.getUser(user);
        if (!users.isEmpty()) {
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
        }
        return "error";
    }
}
