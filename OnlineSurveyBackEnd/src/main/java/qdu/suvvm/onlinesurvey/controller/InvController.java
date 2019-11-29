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
import qdu.suvvm.onlinesurvey.pojo.Tag;
import qdu.suvvm.onlinesurvey.pojo.User;
import qdu.suvvm.onlinesurvey.utils.EmailUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: InvController
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2019/11/27 21:00
 */
@RestController
public class InvController {
    @Autowired
    private InvMapper invMapper;

    @Value("${privateInfo.accessKeyId}")
    private String ak_id;

    @Value("${privateInfo.accessKeySecret}")
    private String ak_secret;

    @PostMapping("/insertInvestigate")
    public String insertInvestigate(HttpServletRequest request) {
        String details = request.getParameter("invDetails");
        String tags = request.getParameter("tags");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Integer ownerId = Integer.parseInt(request.getParameter("ownerId"));
        User owner = new User();
        owner.setId(ownerId);
//        System.out.println(details);
//        System.out.println(tags);
//        System.out.println(name);
//        System.out.println(ownerId);
        List<Tag> tagList = JSONArray.parseArray(tags, Tag.class);

        Investigate investigate = new Investigate();
        investigate.setName(name);
        investigate.setDescription(description);
        investigate.setVisible(false);
        investigate.setDetails(details);
        investigate.setOwner(owner);
        investigate.setTime(new Date());

        int res = invMapper.insertInvestigate(investigate);

        for (Tag tag : tagList) {
            invMapper.insertInvTag(investigate, tag);
        }
        if(res > 0) {
            return "success";
        }
        return "error";
    }

    @GetMapping("/getInvisibleInv")
    public String getInvisibleInv() {
        Investigate investigate = new Investigate();
        investigate.setVisible(false);
        List<Investigate> invList = invMapper.getInvestigate(investigate);
        String res = JSONArray.toJSONString(invList);
//        System.out.println("res!!!" + res);
        return res;
    }

    @PostMapping("/getInvestigate")
    public String getInvestigate(HttpServletRequest request) {
        Investigate investigate = new Investigate();
        if(request.getParameter("id") != null) {
            investigate.setId(Integer.parseInt(request.getParameter("id")));
        }
        if(request.getParameter("name") != null) {
            investigate.setName(request.getParameter("name"));
        }
        if(request.getParameter("ownerId") != null) {
            User owner = new User();
            owner.setId(Integer.parseInt(request.getParameter("ownerId")));
            investigate.setOwner(owner);
        }
        List<Investigate> invList = invMapper.getInvestigate(investigate);
        if(!invList.isEmpty()) {
            return JSONArray.toJSONString(invList);
        }
        return "null";
    }

    @PostMapping("/verifyInv")
    public void verifyInv(@RequestParam("id") String id, @RequestParam("status") String flag, @RequestParam("email") String email, @RequestParam("username") String userName, @RequestParam("ivnName") String invName) {
        if(flag == "0"){
            EmailUtil emailUtil = new EmailUtil();
            emailUtil.sendEmail(email, "PopSurvey审核未通过", "尊敬的 " + userName + "，您的问卷 " + invName + " 审核未通过，请及时修改！", ak_id, ak_secret);
        } else {
            Investigate investigate = new Investigate();
            investigate.setId(Integer.parseInt(id));
            investigate.setVisible(true);
            invMapper.updateInvestigate(investigate);
        }
    }
}
