package qdu.suvvm.onlinesurvey.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.mapper.InvMapper;
import qdu.suvvm.onlinesurvey.pojo.Investigate;
import qdu.suvvm.onlinesurvey.pojo.Result;
import qdu.suvvm.onlinesurvey.pojo.Tag;
import qdu.suvvm.onlinesurvey.pojo.User;
import qdu.suvvm.onlinesurvey.utils.EmailUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: InvController
 * @Description: 处理问卷相关请求
 * @Author: SUVVM
 * @Date: 2019/11/27 21:00
 */
@RestController
public class InvController {
    @Autowired
    private InvMapper invMapper;

    @Autowired
    private User owner;

    @Autowired
    private Investigate investigate;

    @Autowired
    private EmailUtil emailUtil;

    @Value("${privateInfo.accessKeyId}")
    private String ak_id;    // 阿里ak_id配置在application-privateInfo中，由于是敏感数据并未上传

    @Value("${privateInfo.accessKeySecret}")
    private String ak_secret;   // 阿里ak_secret配置在application-privateInfo中，由于是敏感数据并未上传

    /**
     * @FunctionName: insertInvestigate
     * @Description: 处理插入问卷的请求
     * @Parameter:
     *  request 客户机请求
     * @Return: 成功返回"success"，失败返回"error"
     */
    @PostMapping("/insertInvestigate")
    public String insertInvestigate(HttpServletRequest request) {
        owner.reSetUser(null, null, null, null, null,
                null, null, null, null, null, null);
        investigate.reSetInvestigate(null,null,null,null,null,null,null);
        // 获取客户机请求数据
        try {
            String details = request.getParameter("invDetails");
            String tags = request.getParameter("tags");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Integer ownerId = Integer.parseInt(request.getParameter("ownerId"));

            owner.setId(ownerId);
//        System.out.println(details);
//        System.out.println(tags);
//        System.out.println(name);
//        System.out.println(ownerId);
            List<Tag> tagList = JSONArray.parseArray(tags, Tag.class);

            // 根据客户机请求数据初始化问卷信息

            investigate.setName(name);
            investigate.setDescription(description);
            investigate.setVisible(false);
            investigate.setDetails(details);
            investigate.setOwner(owner);
            investigate.setTime(new Date());

            int res = invMapper.insertInvestigate(investigate); // 插入问卷

            for (Tag tag : tagList) {   // 遍历标签列表添加问卷与标签对应关系
                invMapper.insertInvTag(investigate, tag);
            }
            if(res > 0) {   // 问卷表受影响行数大于0
                return "success";
            }
            return "error";
        } finally {
            owner.reSetUser(null, null, null, null, null,
                    null, null, null, null, null, null);
            investigate.reSetInvestigate(null,null,null,null,null,null,null);
        }

    }

    /**
     * @FunctionName: getInvisibleInv
     * @Description: 处理获取未审核问卷的请求
     * @Return: 返回由取得的数据转换为的JSONString
     */
    @GetMapping("/getInvisibleInv")
    public String getInvisibleInv() {
        investigate.reSetInvestigate(null,null,null,null,null,null,null);
        try {
            investigate.setVisible(false);  //未审核问卷
            List<Investigate> invList = invMapper.getInvestigate(investigate);  // 查询问卷
            String res = JSONArray.toJSONString(invList);
//        System.out.println("res!!!" + res);
            return res;
        } finally {
            investigate.reSetInvestigate(null,null,null,null,null,null,null);
        }

    }

    /**
     * @FunctionName: getInvestigate
     * @Description: 处理获得问卷的请求
     * @Parameter:
     *  request 客户机请求
     * @Return: 成功返回由取得的数据转换为的JSONString，数据为空返回"null"
     */
    @PostMapping("/getInvestigate")
    public String getInvestigate(HttpServletRequest request) {
        // 根据客户机请求初始化问卷信息
        owner.reSetUser(null, null, null, null, null,
                null, null, null, null, null, null);
        investigate.reSetInvestigate(null,null,null,null,null,null,null);
        try {
            if(request.getParameter("id") != null) {
                investigate.setId(Integer.parseInt(request.getParameter("id")));
            }
            if(request.getParameter("name") != null) {
                investigate.setName(request.getParameter("name"));
            }
            if(request.getParameter("ownerId") != null) {
                owner.setId(Integer.parseInt(request.getParameter("ownerId")));
                investigate.setOwner(owner);
            }

            List<Investigate> invList = invMapper.getInvestigate(investigate);  // 查询问卷
            if(!invList.isEmpty()) {    // 结果不为空
                return JSONArray.toJSONString(invList);
            }
            return "null";
        } finally {
            owner.reSetUser(null, null, null, null, null,
                    null, null, null, null, null, null);
            investigate.reSetInvestigate(null,null,null,null,null,null,null);
        }

    }

    /**
     * @FunctionName: verifyInv
     * @Description: 处理审核问卷的请求
     * @Parameter:
     *  id 客户机请求中的问卷id
     *  flag 客户机请求中的审核信息
     *  email 客户机请求中的用户邮箱
     *  userName 客户机请求中的用户名
     *  invName 客户机请求中的问卷
     */
    @PostMapping("/verifyInv")
    public void verifyInv(@RequestParam("id") String id, @RequestParam("status") String flag, @RequestParam("email") String email, @RequestParam("username") String userName, @RequestParam("ivnName") String invName) {
        investigate.reSetInvestigate(null,null,null,null,null,null,null);

        try {
            if(flag == "0"){    // 如果审核未通过，向用户发送提示邮件
                emailUtil.sendEmail(email, "PopSurvey审核未通过", "尊敬的 " + userName + "，您的问卷 " + invName + " 审核未通过，请及时修改！", ak_id, ak_secret);
            } else {    // 更新对应问卷visible属性为true
                investigate.setId(Integer.parseInt(id));
                investigate.setVisible(true);
                invMapper.updateInvestigate(investigate);   // 更新问卷
            }
        } finally {
            investigate.reSetInvestigate(null,null,null,null,null,null,null);
        }

    }

    /**
     * @FunctionName: mdfInvestigate
     * @Description: 处理修改问卷的请求
     * @Parameter:
     *  request 客户机请求
     * @Return: 修改成功返回"success"，修改失败返回"error"
     */
    @PostMapping("/mdfInvestigate")
    public String mdfInvestigate(HttpServletRequest request) {
        // 根据客户机请求初始化问卷信息
        investigate.reSetInvestigate(null,null,null,null,null,null,null);
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String description = request.getParameter("description");
            String name = request.getParameter("name");
            String details = request.getParameter("invDetails");
            investigate.setId(id);
            if(!"null".equals(name)) {
                investigate.setName(name);
            }
            if(!"null".equals(description)) {
                investigate.setDescription(description);
            }
            if(!"null".equals(details)) {
                investigate.setDetails(details);
            }
//        System.out.println(investigate);
            if(invMapper.updateInvestigate(investigate) > 0) {  // 更新问卷判断受影响行数
                return "success";
            }
            return "error";
        } finally {
            investigate.reSetInvestigate(null,null,null,null,null,null,null);
        }
    }

    /**
     * @FunctionName: getAllInv
     * @Description: 处理获取全部问卷的请求
     * @Return: 返回全部问卷列表转换为的JSONString
     */
    @GetMapping("/getAllInv")
    public String getAllInv() {
        investigate.reSetInvestigate(null,null,null,null,null,null,null);
        try {
            List<Investigate> invList = invMapper.getInvestigate(investigate);  // 查询问卷
            return JSONArray.toJSONString(invList);
        } finally {
            investigate.reSetInvestigate(null,null,null,null,null,null,null);
        }
    }

    /**
     * @FunctionName: getVisibleInv
     * @Description: 处理获取全部已审核问卷的请求
     * @Return: 返回全部问卷列表转换为的JSONString
     */
    @GetMapping("/getVisibleInv")
    public String getVisibleInv() {
        investigate.reSetInvestigate(null,null,null,null,null,null,null);
        try {
            investigate.setVisible(true);
            List<Investigate> invList = invMapper.getInvestigate(investigate);  // 查询问卷
            return JSONArray.toJSONString(invList);
//        System.out.println("res!!!" + res);
        } finally {
            investigate.reSetInvestigate(null,null,null,null,null,null,null);
        }
    }

    /**
     * @FunctionName: getHistoryByUId
     * @Description: 处理根据作答获取问卷的请求
     * @Parameter:
     *  reqResults 客户机请求中的回答列表JSON串
     * @Return: 返回问卷列表转换为的JSONString，数据为空返回null
     */
    @PostMapping("/getInvsByResults")
    public String getHistoryByUId(@RequestParam("results") String reqResults) {
        investigate.reSetInvestigate(null,null,null,null,null,null,null);
        try {
            // 将回答列表恢复
            List<Result> results = JSONArray.parseArray(reqResults, Result.class);
            // investigates记录要查询的问卷信息
            List<Investigate> investigates = new ArrayList<>();
            for(Result result : results) {  // 遍历回答查询问卷

                investigate.setId(result.getIid()); //根据回答中的问卷id初始问卷信息
                investigates.addAll(invMapper.getInvestigate(investigate)); // 查询问卷
            }
            if(investigates.isEmpty()) {
                return "null";
            }
            return JSONArray.toJSONString(investigates);
        } finally {
            investigate.reSetInvestigate(null,null,null,null,null,null,null);
        }
    }
}
