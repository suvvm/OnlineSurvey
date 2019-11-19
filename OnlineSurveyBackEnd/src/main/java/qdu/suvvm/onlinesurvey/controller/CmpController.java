package qdu.suvvm.onlinesurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.mapper.CmpMapper;
import qdu.suvvm.onlinesurvey.mapper.UserMapper;
import qdu.suvvm.onlinesurvey.pojo.Company;
import qdu.suvvm.onlinesurvey.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: CmpController
 * @Description: 处理关于公司的相关请求
 * @Author: SUVVM
 * @Date: 2019/11/19 20:16
 */
@RestController
public class CmpController {
    @Autowired
    private CmpMapper cmpMapper;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/insertCmp")
    public String insertCmp(HttpServletRequest request) {
        Company company = new Company();
        company.setName(request.getParameter("name"));
        company.setDescription(request.getParameter("description"));
        company.setForms(request.getParameter("forms"));
        company.setDomain(request.getParameter("domain"));
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("ownerid")));
        user.setPower(1);
        company.setOwner(user);
        userMapper.updateUserById(user);
        int res = 0;
        res = cmpMapper.insertCmp(company);
        if (res >= 0) {
            return "success";
        }
        return "error";
    }
}