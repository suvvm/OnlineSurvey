package qdu.suvvm.onlinesurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.mapper.TagMapper;
import qdu.suvvm.onlinesurvey.pojo.Tag;

import java.util.List;

/**
 * @ClassName: DBTestController
 * @Description: 测试数据库访问
 * @Author: SUVVM
 * @Date: 2019/11/15 11:23
 */
@RestController
public class DBTestController {
    @Autowired
    private TagMapper tagMapper;
    @RequestMapping("/dbTest")
    public String test(@RequestParam("name") String name) {
        Tag tag = new Tag();
        tag.setName(name);
        List<Tag> tags = tagMapper.selectTags(tag);
        for(Tag t : tags) {
            System.out.println(t.getId() + " " + t.getName() + " " + t.getDescription());
        }
        return "yes";
    }
    
}
