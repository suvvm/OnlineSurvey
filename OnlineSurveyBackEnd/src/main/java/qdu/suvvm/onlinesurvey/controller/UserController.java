package qdu.suvvm.onlinesurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.mapper.UserMapper;
import qdu.suvvm.onlinesurvey.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2019/11/18 8:57
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/insertUser")
    public String insertUser(HttpServletRequest request) {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setPnum(request.getParameter("pnum"));
        user.setEmail(request.getParameter("email"));
        user.setGender(request.getParameter("gender"));
        user.setAvatar(request.getParameter("avatar"));
        user.setImgbase64(request.getParameter("imgbase64"));
        user.setPower(Integer.parseInt(request.getParameter("power")));
        int res = 0;
        res = userMapper.insertUser(user);
        if(res > 0){
            return "success";
        }
        return "error";
    }
}
