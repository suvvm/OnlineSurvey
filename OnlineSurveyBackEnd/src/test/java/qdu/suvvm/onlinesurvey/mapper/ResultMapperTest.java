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
import qdu.suvvm.onlinesurvey.pojo.Result;
import qdu.suvvm.onlinesurvey.pojo.User;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ResultMapperTest
 * @Description: 针对ResultMapper进行单元测试
 * @Author: SUVVM
 * @Date: 2020/5/13 17:17
 */
@ExtendWith(SpringExtension.class)
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
public class ResultMapperTest {
    @Autowired
    private InvMapper invMapper;
    @Autowired
    private Investigate inv;
    @Autowired
    private User user;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Result result;
    @Autowired
    private ResultMapper resultMapper;

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

        userMapper.insertUserResult(11111111, 31111111, "test ans 1");
        userMapper.insertUserResult(11111111, 32222222, "test ans 2");
        userMapper.insertUserResult(11111111, 33333333, "test ans 3");

        user.reSetUser(null, null, null, null, null,
                null, null, null, null, null, null);
        inv.reSetInvestigate(null,null,null,null,null,null,null);
    }

    @Test
    public void normalSelect() {
        // 测试uid查询
        result.setUid(11111111);
        List<Result> res = resultMapper.getResult(result);
        Assertions.assertEquals(res.size(), 3, "result uid");
        result.setUid(null);

        // 测试iid查询
        result.setIid(31111111);
        res = resultMapper.getResult(result);
        Assertions.assertEquals(res.size(), 1, "result iid");
        result.setIid(null);

        // 测试uid,iid查询
        result.setUid(11111111);
        result.setIid(31111111);
        res = resultMapper.getResult(result);
        Assertions.assertEquals(res.size(), 1, "result iid uid");
        result.setIid(null);
        result.setUid(null);

        // 查询不存在值
        result.setIid(1);
        res = resultMapper.getResult(result);
        Assertions.assertEquals(res.size(), 0, "result invalid");
        result.setIid(null);

    }


    @AfterEach
    public void endTest() {
        resultMapper.deleteResultByUidAndIId(11111111, 31111111);
        resultMapper.deleteResultByUidAndIId(11111111, 32222222);
        resultMapper.deleteResultByUidAndIId(11111111, 33333333);

        invMapper.deleteInvById(31111111);
        invMapper.deleteInvById(32222222);
        invMapper.deleteInvById(33333333);

        userMapper.deleteUserById(11111111);
    }
}
