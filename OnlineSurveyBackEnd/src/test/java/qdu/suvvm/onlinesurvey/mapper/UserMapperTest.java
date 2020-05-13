package qdu.suvvm.onlinesurvey.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import qdu.suvvm.onlinesurvey.pojo.Company;
import qdu.suvvm.onlinesurvey.pojo.User;

import java.util.List;

/**
 * @ClassName: UserMapperTest
 * @Description: 针对UserMapper进行单元测试
 * @Author: SUVVM
 * @Date: 2020/5/13 16:18
 */
@ExtendWith(SpringExtension.class)
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private User user;
    @Autowired
    private UserMapper userMapper;
    @BeforeEach
    public void init() {
        user.reSetUser(11111111, "sametestUName1", "11111111", "testName1", "11111111111",
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
    public void normalSelect() {
        // 测试id查询
        user.setId(11111111);
        List<User> res = userMapper.getUser(user);
        Assertions.assertEquals(res.size(), 1);
        user.setId(null);

        // 测试username查询
        user.setUsername("sametestUName1");
        res = userMapper.getUser(user);
        Assertions.assertEquals(res.size(), 2);
        user.setUsername(null);

        // 测试name查询
        user.setName("testName1");
        res = userMapper.getUser(user);
        Assertions.assertEquals(res.size(), 1);
        user.setName(null);

        // 测试pnum查询
        user.setPnum("33333333333");
        res = userMapper.getUser(user);
        Assertions.assertEquals(res.size(), 1);
        user.setPnum(null);

        // 测试email查询
        user.setEmail("111111@111.111");
        res = userMapper.getUser(user);
        Assertions.assertEquals(res.size(), 1);
        user.setEmail(null);

        // 测试power查询
        user.setPower(2);
        res = userMapper.getUser(user);
        Assertions.assertTrue(res.size() >= 3);
        user.setPower(null);

        // 全字段查询
        user.reSetUser(13333333, "sametestUName1", "33333333", "testName3", "33333333333",
                "333333@333.33", "1", "testAvatarVal", "testImgVal", 2, null);
        res = userMapper.getUser(user);
        Assertions.assertEquals(res.size(), 1);

        // 查询不存在值
        user.setId(1);
        res = userMapper.getUser(user);
        Assertions.assertEquals(res.size() , 0);
        user.setId(null);

        // 全表查询
        user.reSetUser(null, null, null, null, null,
                null, null, null, null, null, null);
        res = userMapper.getUser(user);
        Assertions.assertTrue(res.size() >= 3);

    }

    @Test
    public void invalidDelete() {   // 删除不存在的条目
        Assertions.assertEquals(userMapper.deleteUserById(1), 0);
    }

    @AfterEach
    public void endTest() {
        userMapper.deleteUserById(11111111);
        userMapper.deleteUserById(12222222);
        userMapper.deleteUserById(13333333);
    }
}
