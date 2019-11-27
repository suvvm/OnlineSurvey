package qdu.suvvm.onlinesurvey.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.mapper.InvMapper;
import qdu.suvvm.onlinesurvey.pojo.Investigate;
import qdu.suvvm.onlinesurvey.pojo.Tag;
import qdu.suvvm.onlinesurvey.pojo.User;

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
    public String getInvisibleInv(){
        Investigate investigate = new Investigate();
        investigate.setVisible(false);
        List<Investigate> invList = invMapper.getInvestigate(investigate);
        String res = JSONArray.toJSONString(invList);
        return res;
    }

}
