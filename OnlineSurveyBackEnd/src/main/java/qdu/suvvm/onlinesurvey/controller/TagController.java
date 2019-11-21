package qdu.suvvm.onlinesurvey.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.mapper.TagMapper;
import qdu.suvvm.onlinesurvey.pojo.Tag;

import java.util.List;

/**
 * @ClassName: TagController
 * @Description: 处理tag相关请求
 * @Author: SUVVM
 * @Date: 2019/11/20 20:05
 */
@RestController
public class TagController {
    @Autowired
    private TagMapper tagMapper;
    @GetMapping("/getTags")
    public String getTags() {
        Tag tag = new Tag();
        List<Tag> tags = tagMapper.selectTags(tag);
        String res = JSON.toJSONString(tags);
        return res;
    }
}
