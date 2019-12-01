package qdu.suvvm.onlinesurvey.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qdu.suvvm.onlinesurvey.mapper.TagMapper;
import qdu.suvvm.onlinesurvey.pojo.Investigate;
import qdu.suvvm.onlinesurvey.pojo.Tag;

import java.util.*;

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
        return JSON.toJSONString(tags);
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
    }

    @PostMapping("/getRecommendByTag")
    public String getTagById(@RequestParam("tagList") String tagList) {
        List<Tag> tags = JSONArray.parseArray(tagList, Tag.class);
        List<Tag> resTags = new ArrayList<>();
        for(Tag tag : tags){
            List<Tag> tmp =  tagMapper.selectTags(tag);
            resTags.removeAll(tmp);
            resTags.addAll(tmp);
//            invList.containsAll(tagMapper.selectTags(tag));
        }
        List<Investigate> invList = new ArrayList<>();
        for(Tag tag : resTags){
            invList.removeAll(tag.getInvestigates());
            invList.addAll(tag.getInvestigates());
        }
        invList = removeDuplicateAlarms(invList);
        System.out.println(invList);
        if (!invList.isEmpty()) {
            return JSONArray.toJSONString(invList);
        } else {
            return "null";
        }
    }
    // 去重函数
    private static List<Investigate> removeDuplicateAlarms(List<Investigate> alarms) {
        Set<Investigate> set = new TreeSet<Investigate>(new Comparator<Investigate>() {
            @Override
            public int compare(Investigate o1, Investigate o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        //为了保留最新，所以list需要使用reverse从后往前读取
        Collections.reverse(alarms);
        set.addAll(alarms);
        return new ArrayList<Investigate>(set);
    }
}
