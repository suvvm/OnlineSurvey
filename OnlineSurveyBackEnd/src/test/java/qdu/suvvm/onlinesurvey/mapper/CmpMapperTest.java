package qdu.suvvm.onlinesurvey.mapper;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import qdu.suvvm.onlinesurvey.pojo.*;
import qdu.suvvm.onlinesurvey.pojo.Tag;

import java.util.List;

/**
 * @ClassName: CmpMapperTest
 * @Description: 针对CmpMapper进行单元测试
 * @Author: SUVVM
 * @Date: 2020/5/13 12:06
 */
@ExtendWith(SpringExtension.class)
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
public class CmpMapperTest {
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
    public void normalSelect() {

        // 测试id查询
        company.setId(21111111);
        List<Company> res = cmpMapper.getCompanies(company);
        Assertions.assertEquals(res.size(), 1);
        company.setId(null);

        // 测试name查询
        company.setName("testCmp2");
        res = cmpMapper.getCompanies(company);
        Assertions.assertEquals(res.size(), 1);
        company.setName(null);

        // 测试ownerid查询
        user.setId(11111111);
        company.setOwner(user);
        res = cmpMapper.getCompanies(company);
        Assertions.assertEquals(res.size(), 3);
        company.setOwner(null);
        user.setId(null);

        // 测试forms查询
        company.setForms("外资企业");
        res = cmpMapper.getCompanies(company);
        System.out.println(company);
        System.out.println(res.size());
        for (Company cmp : res)
            System.out.println(res);
        Assertions.assertTrue(res.size() >= 3);
        company.setForms(null);

        // 测试domain查询
        company.setDomain("test");
        res = cmpMapper.getCompanies(company);
        Assertions.assertTrue(res.size() >= 3);
        company.setDomain(null);

        // 测试全部字段
        user.setId(11111111);
        company.reSetCompany(23333333,"testCmp3","test","外资企业","test",user);
        res = cmpMapper.getCompanies(company);
        Assertions.assertEquals(res.size(), 1);
        user.setId(null);

        // 测试不存在值
        company.setId(1);
        res = cmpMapper.getCompanies(company);
        Assertions.assertEquals(res.size(), 0);
        company.setId(null);

        // 查询全表
        company.reSetCompany(null,null,null,null,null,null);
        res = cmpMapper.getCompanies(company);
        Assertions.assertTrue(res.size() >= 3);

    }

    @Test
    public void invalidInsert() {   // 插入不存在的外键
        user.setId(1);
        company.reSetCompany(24444444,"testCmp1","test","外资企业","test", user);
        Assertions.assertThrows(DataIntegrityViolationException.class,
                ()->{ cmpMapper.insertCmpAllProperties(company); });
    }

    @Test
    public void invalidDelete() {   // 删除不存在的条目
        Assertions.assertEquals(cmpMapper.deleteCmpById(1), 0);
    }

    @AfterEach
    public void endTest() {
        cmpMapper.deleteCmpById(21111111);
        cmpMapper.deleteCmpById(22222222);
        cmpMapper.deleteCmpById(23333333);
        userMapper.deleteUserById(11111111);
    }
}
