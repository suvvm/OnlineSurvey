package qdu.suvvm.onlinesurvey.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.mapper.CmpMapper;
import qdu.suvvm.onlinesurvey.mapper.UserMapper;
import qdu.suvvm.onlinesurvey.pojo.Company;
import qdu.suvvm.onlinesurvey.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * @FunctionName: insertCmp
     * @Description: 处理插入公司的请求
     * @Parameter:
     *  request 客户机请求
     * @Return: 插入成功返回字符串"success"失败返回字符串"error"
     */
    @PostMapping("/insertCmp")
    public String insertCmp(HttpServletRequest request) {
        Company company = new Company();
        company.setName(request.getParameter("name"));
        company.setDescription(request.getParameter("description"));
        company.setForms(request.getParameter("forms"));
        company.setDomain(request.getParameter("domain"));

        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("ownerid")));
        company.setOwner(user);

        int res = 0;
        res = cmpMapper.insertCmp(company);
        if (res > 0) {
            user.setPower(1);
            user.setCompany(company);
            userMapper.updateUserById(user);
            return "success";
        }
        return "error";
    }

    @PostMapping("/updateCompany")
    public String updateCompany(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String forms = request.getParameter("forms");
        String domain = request.getParameter("domain");
        String description = request.getParameter("description");
        Company company = new Company();
        company.setId(id);
        if(!name.equals("null")) {
            company.setName(name);
        }
        if(!forms.equals("null")) {
            company.setForms(forms);
        }
        if(!domain.equals("null")) {
            company.setDomain(domain);
        }
        if(!description.equals("null")) {
            company.setDescription(description);
        }
//        System.out.println(company);
//        return "success";
        if(cmpMapper.updateCmp(company) > 0) {
            return "success";
        }
        return "error";
    }

    @PostMapping("/getCompanies")
    public String getCompany(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "ownerId", required = false) String ownerId) {
        Company company = new Company();
        if(id != null)
            company.setId(Integer.parseInt(id));
        if(name != null)
            company.setName(name);
        if(ownerId != null){
            User user = new User();
            user.setId(Integer.parseInt(ownerId));
            company.setOwner(user);
        }
        List<Company> companies = cmpMapper.getCompanies(company);
        if(companies.isEmpty()) {
            return "null";
        }
        return JSONArray.toJSONString(companies);
    }
}
