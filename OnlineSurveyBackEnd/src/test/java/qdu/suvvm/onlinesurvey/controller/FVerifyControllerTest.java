package qdu.suvvm.onlinesurvey.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @ClassName: FVerifyControllerTest
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/13 21:39
 */
@ExtendWith(SpringExtension.class)
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
@AutoConfigureMockMvc
public class FVerifyControllerTest {
    @Value("${privateInfo.accessKeyId}")
    private String ak_id;
    @Value("${privateInfo.accessKeySecret}")
    private String ak_secret;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void fVerifyTest() { // 用于测试人脸识别

        byte[] buffer = null;
        // 读取图片字节数组
        try {
            String imgPath = "src\\main\\resources\\static\\face_suvgm.jpg";
            InputStream in = new FileInputStream(imgPath);
            buffer = new byte[in.available()];
            in.read(buffer);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        String imgBase64 = encoder.encode(Objects.requireNonNull(buffer));
        // System.out.println(imgBase64);

        String url = "/fVerify";
        // 构造请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .param("pnum","17854293661")
                .param("content_1",imgBase64)
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
    }

}
