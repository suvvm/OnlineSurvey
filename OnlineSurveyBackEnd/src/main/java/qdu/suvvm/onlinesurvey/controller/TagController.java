package qdu.suvvm.onlinesurvey.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        List<Tag> tags = tagMapper.selectTagsWithoutUser(tag);
        String res = JSON.toJSONString(tags);
        return res;
    }

    @PostMapping("/updateTags")
    public void updateTags(@RequestParam(value = "add") String add, @RequestParam(value = "del") String del, @RequestParam(value = "mdf") String mdf) {
//        System.out.println(add);
        List<Tag> addTags = JSONArray.parseArray(add, Tag.class);
//        System.out.println("addLen:" + addTags.size());
//        System.out.println(del);
        List<Tag> delTags = JSONArray.parseArray(del, Tag.class);
//        System.out.println("delLen:" + delTags.size());
//        System.out.println(mdf);
        List<Tag> mdfTags = JSONArray.parseArray(mdf, Tag.class);
//        System.out.println("mdfLen:" + mdfTags.size());
        for(Tag tag : addTags) {
            tagMapper.insertTag(tag);
        }
        for(Tag tag : delTags) {
            tagMapper.deleteTagById(tag.getId());
        }
        for(Tag tag : mdfTags) {
            tagMapper.updateTagById(tag);
        }
    };
}
