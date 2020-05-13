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
import qdu.suvvm.onlinesurvey.pojo.Investigate;
import qdu.suvvm.onlinesurvey.pojo.Tag;
import qdu.suvvm.onlinesurvey.pojo.User;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: TagMapperTest
 * @Description: 针对TagMapper进行单元测试
 * @Author: SUVVM
 * @Date: 2020/5/13 17:18
 */
@ExtendWith(SpringExtension.class)
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
public class TagMapperTest {
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
    public void normalSelect() {
        // 测试id查询
        tag.setId(42222222);
        List<Tag> res = tagMapper.selectTags(tag);
        Assertions.assertEquals(res.size(), 1, "tag id");
        for (User u : res.get(0).getUsers()) {
            System.out.println(u);
        }
        Assertions.assertTrue(res.get(0).getUsers().get(0).getId() == 11111111, "tag id user");
        Assertions.assertTrue(res.get(0).getInvestigates().size() >= 2, "tag id inv");
        tag.setId(null);

        // 测试name查询
        tag.setName("testTag2");
        res = tagMapper.selectTags(tag);
        Assertions.assertEquals(res.size(), 1,"tag name");
        tag.setName(null);
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
