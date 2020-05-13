package qdu.suvvm.onlinesurvey.controller;

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
import qdu.suvvm.onlinesurvey.mapper.CmpMapper;
import qdu.suvvm.onlinesurvey.mapper.UserMapper;
import qdu.suvvm.onlinesurvey.pojo.Company;
import qdu.suvvm.onlinesurvey.pojo.User;

import java.util.List;

/**
 * @ClassName: CmpControllerTest
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/13 21:55
 */
@ExtendWith(SpringExtension.class)
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
@AutoConfigureMockMvc
public class CmpControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CmpMapper cmpMapper;
    @Autowired
    private Company company;
    @Autowired
    private User user;

    @BeforeEach
    public void init() {
        user.reSetUser(11111111, "sametestUName1", "11111111", "testName1", "11111111111",
                "111111@111.111", "0", "testAvatarVal", "testImgVal", 0, null);
//        user.reSetUser(12222222, "testUName2", "22222222", "testName2", "22222222222",
//                "222222@222.22", "0", "testAvatarVal", "testImgVal", 0, null);
//        user.reSetUser(13333333, "sametestUName1", "33333333", "testName3", "33333333333",
//                "333333@333.33", "1", "testAvatarVal", "testImgVal", 0, null);

        company.reSetCompany(21111111,"testCmp1","test","外资企业","test",user);
        user.setCompany(company);
        userMapper.insertUserAllProperties(user);
        cmpMapper.insertCmpAllProperties(company);
        company.reSetCompany(22222222,"testCmp2","test","外资企业","test",user);
        cmpMapper.insertCmpAllProperties(company);
        company.reSetCompany(23333333,"testCmp3","test","外资企业","test",user);
        cmpMapper.insertCmpAllProperties(company);

        user.reSetUser(null, null, null, null, null,
                null, null, null, null, null, null);
        company.reSetCompany(null,null,null,null,null,null);
    }

    @Test
    public void addCmpTest() { // 用于测试添加公司
        String url = "/insertCmp";
        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .param("name","testCmp4")
                .param("description","test")
                .param("forms","外资企业")
                .param("domain","test")
                .param("ownerid","11111111")
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应
            // 测试ownerid查询
            user.setId(11111111);
            company.setOwner(user);
            List<Company> res = cmpMapper.getCompanies(company);
            Assertions.assertEquals(res.size(), 4, "ownerid cmp cnt");
            company.setOwner(null);
            user.setId(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateCmpTest() { // 用于测试更新公司
        String url = "/updateCompany";
        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .param("name","testCmp4update")
                .param("description","null")
                .param("forms","null")
                .param("domain","null")
                .param("id","21111111")
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应
            // 测试id查询
            company.setId(21111111);
            List<Company> res = cmpMapper.getCompanies(company);
            Assertions.assertTrue("testCmp4update".equals(res.get(0).getName()), "update cmp name");
            company.setId(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCmpTest() { // 用于测试获取公司
        String url = "/getCompanies";
        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .param("id","23333333")
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应
            // 测试id查询
            company.setId(23333333);
            List<Company> res = cmpMapper.getCompanies(company);
            Assertions.assertTrue("testCmp3".equals(res.get(0).getName()), "update cmp name");
            company.setId(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void endTest() {
        company.setName("testCmp4");
        List<Company> res = cmpMapper.getCompanies(company);
        if (!res.isEmpty()) {
            cmpMapper.deleteCmpById(res.get(0).getId());
        }
        cmpMapper.deleteCmpById(21111111);
        cmpMapper.deleteCmpById(22222222);
        cmpMapper.deleteCmpById(23333333);
        userMapper.deleteUserById(11111111);
    }
}
