package qdu.suvvm.onlinesurvey.controller;

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

/**
 * @ClassName: SmsControllerTest
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/13 20:27
 */
@ExtendWith(SpringExtension.class)
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
@AutoConfigureMockMvc
public class SmsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void smsTest() { // 用于测试验证码发送
        String url = "/getSms";
        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON)
                .param("mobile","15098997526")
                .param("code","{\"code\":\"1234\"}");
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
    }
}
