package qdu.suvvm.onlinesurvey.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import qdu.suvvm.onlinesurvey.mapper.UserMapper;
import qdu.suvvm.onlinesurvey.pojo.Investigate;
import qdu.suvvm.onlinesurvey.pojo.User;
import qdu.suvvm.onlinesurvey.utils.MD5;

import java.util.List;

/**
 * @ClassName: UserControllerTest
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/13 23:51
 */
@ExtendWith(SpringExtension.class)
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private User user;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void init() {
        user.reSetUser(11111111, "sametestUName1", MD5.getMd5("11111111"), "testName1", "11111111111",
                "111111@111.111", "0", "testAvatarVal", "testImgVal", 2, null);
        userMapper.insertUserAllProperties(user);
        user.reSetUser(12222222, "testUName2", "22222222", "testName2", "22222222222",
                "222222@222.22", "0", "testAvatarVal", "testImgVal", 2, null);
        userMapper.insertUserAllProperties(user);
        user.reSetUser(13333333, "sametestUName1", "33333333", "testName3", "33333333333",
                "333333@333.33", "1", "testAvatarVal", "testImgVal", 2, null);
        userMapper.insertUserAllProperties(user);
        user.reSetUser(null, null, null, null, null,
                null, null, null, null, null, null);

    }

    @Test
    public void login() { // 用于测试登录
        String url = "/login";

        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .param("username", "sametestUName1")
                .param("password", "11111111")
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应
            // System.out.println(response.getContentAsString());
            // List<User> list = JSONObject.parseArray(response.getContentAsString(), User.class);

            Assertions.assertTrue("sametestUName1".equals(JSONObject.parseObject(response.getContentAsString(), User.class).getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserById() { // 用于测试获取用户
        String url = "/getUserById";

        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .param("id", "11111111")
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应
            // System.out.println(response.getContentAsString());
            List<User> list = JSONObject.parseArray(response.getContentAsString(), User.class);

            Assertions.assertTrue("sametestUName1".equals(list.get(0).getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUser() { // 用于测试更新用户
        String url = "/updateUser";

        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .param("id", "11111111")
                .param("username", "sametestUName1update")
                .param("name", "null")
                .param("gender", "null")
                .param("password", "null")
                .param("avatar", "null")
                .param("imgBase64", "null")
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应
            // System.out.println(response.getContentAsString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setId(11111111);
        List<User> res = userMapper.getUser(user);
        Assertions.assertTrue("sametestUName1update".equals(res.get(0).getUsername()));
        user.setId(null);
    }


    @AfterEach
    public void endTest() {
        userMapper.deleteUserById(11111111);
        userMapper.deleteUserById(12222222);
        userMapper.deleteUserById(13333333);
    }
}
