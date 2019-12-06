package qdu.suvvm.onlinesurvey.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import qdu.suvvm.onlinesurvey.pojo.Tag;

import java.util.List;

/**
 * @ClassName: TagMapper
 * @Description: dao层 针对taps的数据访问方法
 * @Author: SUVVM
 * @Date: 2019/11/15 10:25
 */
public interface TagMapper {
    // 根据动态sql查询tag
    @SelectProvider(type = TagMapperProvider.class, method = "findTag")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "users", column = "id", many = @Many(select = "qdu.suvvm.onlinesurvey.mapper.UserMapper.getUserByTagId")),
            @Result(property = "investigates", column = "id", many = @Many(select = "qdu.suvvm.onlinesurvey.mapper.InvMapper.getInvByTagId"))
    })
    public List<Tag> selectTags(Tag tag);

    // 获取无用户信息的tag
    @SelectProvider(type = TagMapperProvider.class, method = "findTag")
    public List<Tag> selectTagsWithoutUser(Tag tag);

    // 根据用户id查询 tag
    @Select("select * from tags t inner join usertag ut on t.id=ut.tid where ut.uid=#{uid}")
    public Tag getTagByUserId(Integer uid);

    // 根据问卷id查询 tag
    @Select("select * from tags t inner join invtag it on t.id=it.tid where it.iid=#{iid}")
    public Tag getTagByInvId(Integer iid);

    // 根据id删除tag
    @Delete("delete from tags where id = #{id}")
    public int deleteTagById(Integer id);

    // 插入tag
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into tags(name,description) values (#{name},#{description})")
    public int insertTag(Tag tag);

    // 根据id更新tag
    @UpdateProvider(type = TagMapperProvider.class, method = "updateTag")
    public int updateTagById(Tag tag);


    /**
     * @ClassName: TagMapperProvider
     * @Description: tag动态sql的Provider类
     * @Author: SUVVM
     * @Date: 2019/11/15 10:30
     */
    class TagMapperProvider {
        /**
         * @FunctionName: findTag
         * @Description: 用于生成查询标签动态sql
         * @Parameter:
         *  user 查询数据封装的标签类
         * @Return: 返回对应的sql语句
         */
        public String findTag(Tag tag){
            return new SQL() {
                {
                    SELECT("*");
                    FROM("tags");
                    if(tag.getId() != null) {
                        WHERE("id = #{id}");
                    }
                    if(tag.getName() != null) {
                        WHERE("name = #{name}");
                    }
                }
            }.toString();
        }
        /**
         * @FunctionName: updateTag
         * @Description: 用于生成更新标签动态sql
         * @Parameter:
         *  user 更新数据封装的标签类
         * @Return: 返回对应的sql语句
         */
        public String updateTag(Tag tag) {
            return new SQL() {
                {
                    UPDATE("tags");
                    if(tag.getName() != null) {
                        SET("name = #{name}");
                    }
                    if(tag.getDescription() != null) {
                        SET("description = #{description}");
                    }
                    WHERE("id = #{id}");
                }
            }.toString();
        }
    }
}

