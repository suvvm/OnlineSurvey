package qdu.suvvm.onlinesurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.mapper.UserMapper;
import qdu.suvvm.onlinesurvey.pojo.User;
import qdu.suvvm.onlinesurvey.utils.FVerifyUtil;

import java.util.List;

/**
 * @ClassName: FVerifyController
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2019/11/18 22:06
 */
@RestController
public class FVerifyController {
    @Autowired
    private UserMapper userMapper;

    public String FVerify(@RequestParam("pnum") String pnum, @RequestParam("content_1") String content_1) {
        int type = 1;
        String url = "https://dtplus-cn-shanghai.data.aliyuncs.com/face/verify";
        String ak_id = "yourAccessKid";
        String ak_secret = "yourSecret";
        User user = new User();
        user.setPnum(pnum);
        List<User> users = userMapper.getUser(user);
        if (!users.isEmpty()) {
            String content_2 = users.get(0).getImgbase64();
            String body = "{\"type\":\""+type+"\",\"content_1\":\""+content_1+"\",\"content_2\":\""+content_2+"\"}";
            String res = null;
            try {
                res = FVerifyUtil.sendPost(url, body, ak_id, ak_secret);
                return res;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "error";
    }
}
