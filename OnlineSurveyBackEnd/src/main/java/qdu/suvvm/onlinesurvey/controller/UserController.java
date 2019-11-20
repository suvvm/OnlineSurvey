package qdu.suvvm.onlinesurvey.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.mapper.UserMapper;
import qdu.suvvm.onlinesurvey.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName: UserController
 * @Description: 处理关于用户的相关请求
 * @Author: SUVVM
 * @Date: 2019/11/18 8:57
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * @FunctionName: insertUser
     * @Description: 处理插入用户的请求
     * @Parameter:
     *  request 客户机请求
     * @Return: 插入成功返回字符串"success"失败返回字符串"error"
     */
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

    /**
     * @FunctionName: login
     * @Description: 处理登录请求
     * @Parameter:
     *  username 客户机请求参数中的用户识别信息
     *  password 客户机请求参数中的用户密码信息
     * @Return: 无法查找到用户返回"null",查询成功返回由对应用户信息构成的JSON串,失败返回字符串"error"
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username); // 以传入的信息作为用户名查询用户
        user.setPassword(password);
        List<User> users = userMapper.getUser(user);
        if (users.isEmpty()) {  // 以传入的信息作为电话查询用户
            user.setUsername(null);
            user.setPnum(username);
            users = userMapper.getUser(user);
            if (users.isEmpty()) {  // 以传入的信息作为邮箱查询用户
                user.setPnum(null);
                user.setEmail(username);
                users = userMapper.getUser(user);
                if (users.isEmpty()) {
                    return "null";
                }
            }
        }

        // 创建JSONObject将查询到的用户数据都装入JSON对象
        JSONObject resObj = new JSONObject();
        resObj.put("id", users.get(0).getId());
        resObj.put("username", users.get(0).getUsername());
        resObj.put("name", users.get(0).getName());
        resObj.put("email", users.get(0).getEmail());
        resObj.put("gender", users.get(0).getGender().toString());
        resObj.put("power", users.get(0).getPower());
        // resObj.put("avatar", users.get(0).getAvatar());

        // 将JSON对象转为JSON串发送给前端
        return resObj.toJSONString();
    }
}
