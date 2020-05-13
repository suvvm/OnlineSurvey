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
    @Autowired
    private Tag tag;

    /**
     * @FunctionName: getTags
     * @Description: 处理获取标签的请求
     * @Return: 返回由取得的标签数据列表转换为的JSONString
     */
    @GetMapping("/getTags")
    public String getTags() {
        tag.reSetTag(null, null,null);
        try {
            List<Tag> tags = tagMapper.selectTagsWithoutUser(tag);
            return JSON.toJSONString(tags);
        } finally {
            tag.reSetTag(null, null,null);
        }
    }

    /**
     * @FunctionName: updateTags
     * @Description: 处理添加、修改、删除标签的请求
     * @Parameter:
     *  add 客户机请求中要添加的标签信息
     *  del 客户机请求中要删除的标签信息
     *  mdf 客户机请求中要修改的标签信息
     */
    @PostMapping("/updateTags")
    public void updateTags(@RequestParam(value = "add") String add, @RequestParam(value = "del") String del, @RequestParam(value = "mdf") String mdf) {
//        System.out.println(add);
        // 将添加标签的信息恢复为标签列表
        List<Tag> addTags = JSONArray.parseArray(add, Tag.class);
//        System.out.println("addLen:" + addTags.size());
//        System.out.println(del);
        // 将删除标签的信息恢复为标签列表
        List<Tag> delTags = JSONArray.parseArray(del, Tag.class);
//        System.out.println("delLen:" + delTags.size());
//        System.out.println(mdf);
        // 将修改标签的信息恢复为标签列表
        List<Tag> mdfTags = JSONArray.parseArray(mdf, Tag.class);
//        System.out.println("mdfLen:" + mdfTags.size());
        for(Tag tag : addTags) {    // 遍历添加标签列表添加标签
            tagMapper.insertTag(tag);
        }
        for(Tag tag : delTags) {    // 遍历删除标签列表删除标签
            tagMapper.deleteTagById(tag.getId());
        }
        for(Tag tag : mdfTags) {    // 遍历修改标签列表修改标签
            tagMapper.updateTagById(tag);
        }
    }

    /**
     * @FunctionName: getRecommendByTag
     * @Description: 处理根据标签推荐问卷的请求
     * @Parameter:
     *  tagList 客户机请求中提供的标签列表
     * @Return: 返回由取得的问卷数据列表转换为的JSONString
     */
    @PostMapping("/getRecommendByTag")
    public String getTagById(@RequestParam("tagList") String tagList) {
        // 将标签列表JSON串恢复为标签列表
        List<Tag> tags = JSONArray.parseArray(tagList, Tag.class);

        List<Tag> resTags = new ArrayList<>();
        for(Tag tag : tags){    // 补全标签信息，由于标签与问卷为多对多映射，所以在补全信息的过程中可以获得该标签对应的问卷信息
            List<Tag> tmp =  tagMapper.selectTags(tag);
            // 去重操作
            resTags.removeAll(tmp);
            resTags.addAll(tmp);
//            invList.containsAll(tagMapper.selectTags(tag));
        }
        List<Investigate> invList = new ArrayList<>();
        for(Tag tag : resTags){ // 遍历补全后的标签信息 获取其对应问卷
            // 由于数据结构太过复杂，所以这里去重可能失效，需要手动去重
            invList.removeAll(tag.getInvestigates());
            invList.addAll(tag.getInvestigates());
        }
        invList = removeDuplicateAlarms(invList);   // 手动去重
//        System.out.println(invList);
        if (!invList.isEmpty()) {   // 推荐不为空
            return JSONArray.toJSONString(invList);
        } else {
            return "null";
        }
    }

    /**
     * @FunctionName: removeDuplicateAlarms
     * @Description: 问卷列表手动去重函数，根据id去重
     * @Parameter:
     *  alarms 要执行去重操作的问卷列表
     * @Return: 返回去重后的问卷列表
     */
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
