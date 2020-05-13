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
import qdu.suvvm.onlinesurvey.mapper.InvMapper;
import qdu.suvvm.onlinesurvey.mapper.UserMapper;
import qdu.suvvm.onlinesurvey.pojo.Investigate;
import qdu.suvvm.onlinesurvey.pojo.User;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: InvControllerTest
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/13 22:55
 */
@ExtendWith(SpringExtension.class)
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
@AutoConfigureMockMvc
public class InvControllerTest {
    @Autowired
    private InvMapper invMapper;
    @Autowired
    private Investigate inv;
    @Autowired
    private User user;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        user.reSetUser(11111111, "sametestUName1", "11111111", "testName1", "11111111111",
                "suvvm@foxmail.com", "0", "testAvatarVal", "testImgVal", 2, null);
        userMapper.insertUserAllProperties(user);
        inv.reSetInvestigate(31111111,"sameTestInv1", false, "testInv1Dis","[]",user, new Date());
        invMapper.insertInvestigateAllProperties(inv);
        inv.reSetInvestigate(32222222,"testInv2", true, "testInv2Dis","[]",user, new Date());
        invMapper.insertInvestigateAllProperties(inv);
        inv.reSetInvestigate(33333333,"sameTestInv1", false, "testInv3Dis","[]",user, new Date());
        invMapper.insertInvestigateAllProperties(inv);
        user.reSetUser(null, null, null, null, null,
                null, null, null, null, null, null);
        inv.reSetInvestigate(null,null,null,null,null,null,null);
    }

    @Test
    public void getInvisibleInv() { // 用于测试获取未审核问卷
        String url = "/getInvisibleInv";

        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应
            // System.out.println(response.getContentAsString());
            List<Investigate> list = JSONObject.parseArray(response.getContentAsString(),Investigate.class);
            Assertions.assertTrue(list.size() >= 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getVisibleInv() { // 用于测试获取已审核问卷
        String url = "/getVisibleInv";

        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应
            // System.out.println(response.getContentAsString());
            List<Investigate> list = JSONObject.parseArray(response.getContentAsString(),Investigate.class);
            Assertions.assertTrue(list.size() >= 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getInvestigate() { // 用于测试获取指定问卷
        String url = "/getInvestigate";

        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .param("id", "32222222")
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应
            // System.out.println(response.getContentAsString());
            List<Investigate> list = JSONObject.parseArray(response.getContentAsString(), Investigate.class);
            Assertions.assertTrue("testInv2".equals(list.get(0).getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void verifyInv() { // 测试审核问卷
        String url = "/verifyInv";

        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .param("id", "32222222")
                .param("status", "0")
                .param("email", "suvvm@foxmail.com")
                .param("username", "testUser")
                .param("ivnName", "testInv")
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 构造请求
        requestBuilder = MockMvcRequestBuilders.post(url)
                .param("id", "32222222")
                .param("status", "1")
                .param("email", "suvvm@foxmail.com")
                .param("username", "testUser")
                .param("ivnName", "testInv")
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应

            // 测试id查询
            inv.setId(32222222);
            List<Investigate> res = invMapper.getInvestigate(inv);
            Assertions.assertEquals(res.get(0).getVisible(), true);
            inv.setId(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mdfInvestigate() { // 测试修改问卷
        String url = "/mdfInvestigate";

        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .param("id", "32222222")
                .param("description", "null")
                .param("name", "testInv2update")
                .param("invDetails", "null")
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应

            // 测试id查询
            inv.setId(32222222);
            List<Investigate> res = invMapper.getInvestigate(inv);
            Assertions.assertTrue("testInv2update".equals(res.get(0).getName()));
            inv.setId(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void endTest() {
        invMapper.deleteInvById(31111111);
        invMapper.deleteInvById(32222222);
        invMapper.deleteInvById(33333333);

        userMapper.deleteUserById(11111111);
    }
}
