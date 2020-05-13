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
import qdu.suvvm.onlinesurvey.pojo.User;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: InvMapperTest
 * @Description: 针对CmpMapper进行单元测试
 * @Author: SUVVM
 * @Date: 2020/5/13 17:18
 */
@ExtendWith(SpringExtension.class)
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
public class InvMapperTest {
    @Autowired
    private InvMapper invMapper;
    @Autowired
    private Investigate inv;
    @Autowired
    private User user;
    @Autowired
    private UserMapper userMapper;
    @BeforeEach
    public void init() {
        user.reSetUser(11111111, "sametestUName1", "11111111", "testName1", "11111111111",
                "111111@111.111", "0", "testAvatarVal", "testImgVal", 2, null);
        userMapper.insertUserAllProperties(user);
        inv.reSetInvestigate(31111111,"sameTestInv1", false, "testInv1Dis","[]",user, new Date());
        invMapper.insertInvestigateAllProperties(inv);
        inv.reSetInvestigate(32222222,"testInv2", false, "testInv2Dis","[]",user, new Date());
        invMapper.insertInvestigateAllProperties(inv);
        inv.reSetInvestigate(33333333,"sameTestInv1", false, "testInv3Dis","[]",user, new Date());
        invMapper.insertInvestigateAllProperties(inv);
        user.reSetUser(null, null, null, null, null,
                null, null, null, null, null, null);
        inv.reSetInvestigate(null,null,null,null,null,null,null);
    }

    @Test
    public void normalSelect() {
        // 测试id查询
        inv.setId(31111111);
        List<Investigate> res = invMapper.getInvestigate(inv);
        Assertions.assertEquals(res.size(), 1);
        inv.setId(null);

        // 测试name查询
        inv.setName("sameTestInv1");
        res = invMapper.getInvestigate(inv);
        Assertions.assertEquals(res.size(), 2);
        inv.setName(null);

        // 测试ownerid查询
        user.setId(11111111);
        inv.setOwner(user);
        res = invMapper.getInvestigate(inv);
        Assertions.assertTrue(res.size() >= 3);
        inv.setOwner(null);
        user.setId(null);

        // 测试visible查询
        inv.setVisible(false);
        res = invMapper.getInvestigate(inv);
        Assertions.assertTrue(res.size() >= 3);
        inv.setVisible(null);

        // 全字段查询 时间除外
        user.setId(11111111);
        inv.reSetInvestigate(33333333,"sameTestInv1", false, "testInv3Dis","[]",user, null);
        res = invMapper.getInvestigate(inv);
        Assertions.assertEquals(res.size(), 1);
        user.setId(null);

        // 查询不存在值
        inv.setId(1);
        res = invMapper.getInvestigate(inv);
        Assertions.assertEquals(res.size() , 0);
        inv.setId(null);

        // 全表查询
        inv.reSetInvestigate(null,null,null,null,null,null,null);
        res = invMapper.getInvestigate(inv);
        Assertions.assertTrue(res.size() >= 3);

    }

    @Test
    public void invalidDelete() {   // 删除不存在的条目
        Assertions.assertEquals(invMapper.deleteInvById(1), 0);
    }

    @AfterEach
    public void endTest() {
        invMapper.deleteInvById(31111111);
        invMapper.deleteInvById(32222222);
        invMapper.deleteInvById(33333333);

        userMapper.deleteUserById(11111111);
    }


}
