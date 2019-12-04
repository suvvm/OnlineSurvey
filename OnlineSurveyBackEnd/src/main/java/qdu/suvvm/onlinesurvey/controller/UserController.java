package qdu.suvvm.onlinesurvey.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.mapper.UserMapper;
import qdu.suvvm.onlinesurvey.pojo.User;
import qdu.suvvm.onlinesurvey.utils.MD5;

import javax.lang.model.element.NestingKind;
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
     * @Return: 插入成功返回插入的user转换为的JSON字符串，失败返回字符串"error"
     */
    @PostMapping("/insertUser")
    public String insertUser(HttpServletRequest request) {
        // 根据客户机请求初始化用户信息
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(MD5.getMd5(request.getParameter("password")));
        user.setName(request.getParameter("name"));
        user.setPnum(request.getParameter("pnum"));
        user.setEmail(request.getParameter("email"));
        user.setGender(request.getParameter("gender"));
        user.setAvatar(request.getParameter("avatar"));
        user.setImgbase64(request.getParameter("imgbase64"));
        user.setPower(Integer.parseInt(request.getParameter("power")));
        int res = 0;
        res = userMapper.insertUser(user);  // 插入用户并返回受影响行数
        if(res > 0){    // 受影响行数大于0
            return JSON.toJSONString(user);
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
        user.setPassword(MD5.getMd5(password));
        List<User> users = userMapper.getUser(user);   // 查询用户
        if (users.isEmpty()) {  // 以传入的信息作为电话查询用户
            user.setUsername(null);
            user.setPnum(username);
            users = userMapper.getUser(user);   // 查询用户
            if (users.isEmpty()) {  // 以传入的信息作为邮箱查询用户
                user.setPnum(null);
                user.setEmail(username);
                users = userMapper.getUser(user);   // 查询用户
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
//        resObj.put("avatar", users.get(0).getAvatar());

        // 将JSON对象转为JSON串发送给前端
        return resObj.toJSONString();
    }

    /**
     * @FunctionName: insertUserTag
     * @Description: 处理插入用户标签关系的请求
     * @Parameter:
     *  tags 客户机请求中的标签列表
     *  uid 客户机请求中的用户id
     * @Return: 成功返回由取得的数据转换为的JSONString
     */
    @PostMapping("/insertUserTag")
    public void insertUserTag(@RequestParam("tags") String tagIdList, @RequestParam("uid") String uid) {
//        System.out.println(tagIdList);
        // 将客户机请求中标签列表JSON串恢复为标签列表
        List<Integer> tags = JSONArray.parseArray(tagIdList, Integer.class);

        for(Integer tid : tags) {   // 遍历标签列表，插入用户标签对应关系
//            System.out.println(tid);
            userMapper.insertUserTag(Integer.parseInt(uid), tid);   // 插入用户标签对应关系
        }
    }

    /**
     * @FunctionName: insertUserInv
     * @Description: 处理插入用户作答的请求
     * @Parameter:
     *  iid 客户机请求中的问卷id
     *  uid 客户机请求中的用户id
     *  ans 客户机请求中的用户答案信息
     * @Return: 成功返回"success"，否则返回"error"
     */
    @PostMapping("/insertUserInv")
    public String insertUserInv(@RequestParam("uid") String uid, @RequestParam("iid") String iid, @RequestParam("ans") String ans) {
//        System.out.println(uid);
//        System.out.println(iid);
//        System.out.println(ans);
        int res = userMapper.insertUserResult(Integer.parseInt(uid), Integer.parseInt(iid), ans);   // 插入对应作答
        if(res > 0){    // 作答表受影响行数大于0
            return "success";
        }
        return "error";
    }

    /**
     * @FunctionName: getUserById
     * @Description: 处理根据id获取用户的请求
     * @Parameter:
     *  id 客户机请求中的用户id
     * @Return: 成功返回由取得的数据转换为的JSONString，否则返回"error"
     */
    @PostMapping("/getUserById")
    public String getUserById(@RequestParam("id") String id) {
        // 根据客户机请求中的id初始化用户信息
        User user = new User();
        user.setId(Integer.parseInt(id));
        List<User> users = userMapper.getUser(user);    // 查询用户
        if(!users.isEmpty()){   // 有数据
            return JSON.toJSONString(users.get(0));
        }
        return "error";
    }

    /**
     * @FunctionName: updateUser
     * @Description: 处理更新用户信息的请求
     * @Parameter:
     *  request 客户机请求
     * @Return: 成功返回"success"，否则返回"error"
     */
    @PostMapping("/updateUser")
    public String updateUser(HttpServletRequest request) {
        // 获取客户机请求中的数据
        Integer id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String password = MD5.getMd5(request.getParameter("password"));
        String avatar = request.getParameter("avatar");
        String imgBase64 = request.getParameter("imgBase64");
        // 根据客户机请求数据初始化用户信息
        User user = new User();
        user.setId(id);
        if(!"null".equals(username)) {
            user.setUsername(username);
        }
        if(!"null".equals(name)) {
            user.setName(name);
        }
        if(!"null".equals(gender)) {
            user.setGender(gender);
        }
        if(!"null".equals(password)) {
            user.setPassword(password);
        }
        if(!"null".equals(avatar)) {
            user.setAvatar(avatar);
        }
        if(!"null".equals(imgBase64)) {
            user.setImgbase64(imgBase64);
        }
        if(userMapper.updateUserById(user) > 0) {   // 更新用户受影响行数大于0
            return "success";
        }
        return "error";
    }


    /**
     * @FunctionName: getUsers
     * @Description: 处理获取用户信息的请求
     * @Parameter:
     *  id 客户机请求中的用户id
     *  username 客户机请求中的用户名
     *  name 客户机请求中的用户真实姓名
     *  pnum 客户机请求中的用户手机号
     *  email 客户机请求中的用户邮箱
     *  gender 客户机请求中的用户性别
     *  power 客户机请求中的用户权限
     * @Return: 成功返回由取得的数据转换为的JSONString，数据为空返回"null"
     */
    @PostMapping("/getUsers")
    public String getUsers(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "username", required = false) String username,
                           @RequestParam(value = "name", required = false) String name, @RequestParam(value = "pnum", required = false) String pnum,
                           @RequestParam(value = "email", required = false) String email, @RequestParam(value = "gender", required = false) String gender,
                           @RequestParam(value = "power", required = false) String power) {
        // 根据客户机请求数据初始化用户信息
        User user = new User();
        if(id != null)
            user.setId(Integer.parseInt(id));
        if(username != null)
            user.setUsername(username);
        if(name != null)
            user.setName(name);
        if(pnum != null)
            user.setPnum(pnum);
        if(email != null)
            user.setEmail(email);
        if(gender != null)
            user.setGender(gender);
        if(power != null)
            user.setPower(Integer.parseInt(power));
        List<User> users = userMapper.getUser(user);    // 查询用户
        if(users.isEmpty()) // 数据为空
            return "null";
        return JSONArray.toJSONString(users);
    }

    /**
     * @FunctionName: userEOP
     * @Description: 处理提升用户权限的请求
     * @Parameter:
     *  id 客户机请求中的用户id
     *  power 客户机请求中的权限值
     * @Return: 成功返回"success"，否则返回"error"
     */
    @PostMapping("/userEOP")
    public String userEOP(@RequestParam(value = "id") String id, @RequestParam(value = "power") String power) {
        // 根据客户机请求数据初始化用户信息
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setPower(Integer.parseInt(power));
        if(userMapper.updateUserById(user) > 0) {   // 更新信息
            return "success";
        }
        return "error";
    }
}
