package qdu.suvvm.onlinesurvey;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import qdu.suvvm.onlinesurvey.mapper.TagMapper;
import qdu.suvvm.onlinesurvey.mapper.UserMapper;
import qdu.suvvm.onlinesurvey.pojo.Tag;
import qdu.suvvm.onlinesurvey.pojo.User;

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

    @Autowired
    private UserMapper userMapper;
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

    @Test
    public void insertTag() {
        Tag t = new Tag();
        t.setName("体罚");
        t.setDescription("听说，你在训练中有打骂体罚战士的现象？ 有。 好样的！");
        int res = tagMapper.insertTag(t);
        System.out.println(res);
    }

    /**
     * @FunctionName: insertUserTest
     * @Description: 用于测试插入用户
     */
    @Test
    public void insertUserTest() {
        User user = new User();
        user.setUsername("suvvm");
        user.setPassword("poiuytrewq");
        user.setName("江民");
        user.setPnum("15098997526");
        user.setEmail("suvvm@foxmail.com");
        user.setGender("0");
        user.setAvatar("asdasd");
        user.setImgbase64("asdasd");
        user.setPower(0);
        // t.setId(400000001);

        userMapper.insertUser(user);

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

    @Value("${privateInfo.accessKeyId}")
    private String ak_id;

    @Value("${privateInfo.accessKeySecret}")
    private String ak_secret;
    @Test
    public void showAccessKeyInfo () {
        System.out.println(ak_id + " " + ak_secret);
    }


}
