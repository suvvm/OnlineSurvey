package qdu.suvvm.onlinesurvey;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import qdu.suvvm.onlinesurvey.mapper.TagMapper;
import qdu.suvvm.onlinesurvey.pojo.Tag;

import java.util.List;

@ExtendWith(SpringExtension.class)
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
@AutoConfigureMockMvc
class OnlinesurveyApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TagMapper tagMapper;

    /**
     * @FunctionName: selectTest
     * @Description: 用于测试数据库查询
     */
    @Test
    public void selectTest() {
        Tag t = new Tag();
        // t.setId(400000001);
        t.setName("秃头");
        List<Tag> tags = tagMapper.selectTags(t);
        for(Tag tag : tags){
            System.out.println(tag.getId() + " " + tag.getName() + " " + tag.getDescription());
        }
    }

    /**
     * @FunctionName: smsTest
     * @Description: 用于测试验证码发送
     */
    @Test
    public void smsTest() {
        String url = "/getSms";
        try {
            mockMvc.perform(
                    MockMvcRequestBuilders.post(url)
                    .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .param("mobile","15098997526")
                    .param("code","{\"code\":\"1234\"}")
                    .accept(MediaType.APPLICATION_JSON)
            ).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
