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
import qdu.suvvm.onlinesurvey.mapper.TagMapper;
import qdu.suvvm.onlinesurvey.mapper.UserMapper;
import qdu.suvvm.onlinesurvey.pojo.Investigate;
import qdu.suvvm.onlinesurvey.pojo.Tag;
import qdu.suvvm.onlinesurvey.pojo.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: TagControllerTest
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/13 23:28
 */
@ExtendWith(SpringExtension.class)
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTest {
    @Autowired
    private User user;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Tag tag;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private InvMapper invMapper;
    @Autowired
    private Investigate inv;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        user.reSetUser(11111111, "testUName1", "11111111", "testName1", "11111111111",
                "111111@111.111", "0", "testAvatarVal", "testImgVal", 2, null);
        userMapper.insertUserAllProperties(user);
        user.reSetUser(22222222, "testUName2", "22222222", "testName2", "22222222222",
                "222222@222.222", "0", "testAvatarVal", "testImgVal", 2, null);
        userMapper.insertUserAllProperties(user);
        inv.reSetInvestigate(31111111,"TestInv1", false, "testInv1Dis","[]",user, new Date());
        invMapper.insertInvestigateAllProperties(inv);
        inv.reSetInvestigate(32222222,"testInv2", false, "testInv2Dis","[]",user, new Date());
        invMapper.insertInvestigateAllProperties(inv);

        tag.reSetTag(41111111,"testTag1", "test");
        tagMapper.insertTagAllProperties(tag);
        tag.reSetTag(42222222,"testTag2", "test");
        tagMapper.insertTagAllProperties(tag);
        tag.reSetTag(43333333,"testTag3", "test");
        tagMapper.insertTagAllProperties(tag);

        userMapper.insertUserTag(11111111, 41111111);
        userMapper.insertUserTag(11111111, 42222222);
        userMapper.insertUserTag(22222222, 43333333);

        invMapper.insertInvTagById(31111111, 41111111);
        invMapper.insertInvTagById(31111111, 42222222);
        invMapper.insertInvTagById(32222222, 42222222);

        tag.reSetTag(null,null,null);
        user.reSetUser(null, null, null, null, null,
                null, null, null, null, null, null);
        inv.reSetInvestigate(null,null,null,null,null,null,null);
    }

    @Test
    public void getTags() { // 用于测试获取全部tag
        String url = "/getTags";

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
            List<Tag> list = JSONObject.parseArray(response.getContentAsString(), Tag.class);
            Assertions.assertTrue(list.size() >= 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getRecommendByTag() { // 用于测试根据tag推荐问卷
        String url = "/getRecommendByTag";
        List<Tag> tagList = new ArrayList<>();
        tag.setId(42222222);
        tagList.add(tag);
        String tagListJson = JSONObject.toJSONString(tagList);
        tag.setId(null);
        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .param("tagList", tagListJson)
                .accept(MediaType.APPLICATION_JSON);
        try {
            ResultActions perform = mockMvc.perform(requestBuilder);    // 发送请求获取结果
            perform.andDo(MockMvcResultHandlers.print());   // 添加一个结果处理器将请求打印在控制台
            perform.andExpect(MockMvcResultMatchers.status().isOk());   // 验证结果
            MvcResult mvcResult = perform.andReturn();  // 获取响应
            MockHttpServletResponse response = mvcResult.getResponse(); // 获取响应
            System.out.println(response);   // 打印响应
            List<Investigate> list = JSONObject.parseArray(response.getContentAsString(), Investigate.class);
            Assertions.assertEquals(list.size(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void endTest() {
        userMapper.deleteUserTag(11111111, 41111111);
        userMapper.deleteUserTag(11111111, 42222222);
        userMapper.deleteUserTag(22222222, 43333333);

        invMapper.deleteInvTag(31111111, 41111111);
        invMapper.deleteInvTag(31111111, 42222222);
        invMapper.deleteInvTag(32222222, 42222222);

        tagMapper.deleteTagById(41111111);
        tagMapper.deleteTagById(42222222);
        tagMapper.deleteTagById(43333333);

        invMapper.deleteInvById(31111111);
        invMapper.deleteInvById(32222222);

        userMapper.deleteUserById(11111111);
        userMapper.deleteUserById(22222222);
    }
}
