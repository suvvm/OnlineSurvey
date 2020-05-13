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

    @Autowired
    private Company company;

    @Autowired
    private User user;

    /**
     * @FunctionName: insertCmp
     * @Description: 处理插入公司的请求
     * @Parameter:
     *  request 客户机请求
     * @Return: 插入成功返回字符串"success"失败返回字符串"error"
     */
    @PostMapping("/insertCmp")
    public String insertCmp(HttpServletRequest request) {
        company.reSetCompany(null, null, null, null, null, null);
        user.reSetUser(null, null, null, null, null,
                null, null, null, null, null, null);

        try {
            company.setName(request.getParameter("name"));
            company.setDescription(request.getParameter("description"));
            company.setForms(request.getParameter("forms"));
            company.setDomain(request.getParameter("domain"));

            // User user = new User();
            user.setId(Integer.parseInt(request.getParameter("ownerid")));
            company.setOwner(user);

            int res = 0;
            res = cmpMapper.insertCmp(company); // 插入公司
            if (res > 0) {  // 受影响行数大于0
                user.setPower(1);
                user.setCompany(company);
                userMapper.updateUserById(user);    // 更新用户权限
                return "success";
            }
            return "error";
        } finally {
            user.reSetUser(null, null, null, null, null,
                    null, null, null, null, null, null);
            company.reSetCompany(null, null, null, null, null, null);
        }

    }

    /**
     * @FunctionName: updateCompany
     * @Description: 处理更新公司请求
     * @Parameter:
     *  request 客户机请求
     * @Return: 更新成功返回字符串"success"失败返回字符串"error"
     */
    @PostMapping("/updateCompany")
    public String updateCompany(HttpServletRequest request) {
        company.reSetCompany(null, null, null, null, null, null);
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String forms = request.getParameter("forms");
            String domain = request.getParameter("domain");
            String description = request.getParameter("description");

            // 根据客户机请求数据初始化公司信息
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
            if(cmpMapper.updateCmp(company) > 0) {  // 更新公司受影响行数大于0
                return "success";
            }
            return "error";
        } finally {
            company.reSetCompany(null, null, null, null, null, null);
        }

    }

    /**
     * @FunctionName: getCompany
     * @Description: 处理获取公司请求
     * @Parameter:
     *  id 客户机请求中的公司id
     *  name 客户机请求中的公司名称
     *  ownerId 客户机请求中的公司拥有者id
     * @Return: 更新成功返回查询出的公司数据列表转换为的JSON字符串，没有找到数据返回字符串"null"
     */
    @PostMapping("/getCompanies")
    public String getCompany(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "ownerId", required = false) String ownerId) {
        company.reSetCompany(null, null, null, null, null, null);

        user.reSetUser(null, null, null, null, null,
                null, null, null, null, null, null);

        try { // 根据客户机数据初始化公司信息
            if(id != null)
                company.setId(Integer.parseInt(id));
            if(name != null)
                company.setName(name);
            if(ownerId != null){
                user.setId(Integer.parseInt(ownerId));
                company.setOwner(user);
            }
            List<Company> companies = cmpMapper.getCompanies(company);  // 查询公司
            if(companies.isEmpty()) {   // 查询后列表为空
                return "null";
            }
            // fastJson 将companies转为JSONString
            return JSONArray.toJSONString(companies);
        } finally {
            user.reSetUser(null, null, null, null, null,
                    null, null, null, null, null, null);
            company.reSetCompany(null, null, null, null, null, null);
        }

    }
}
