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
    public List<Tag> selectTags(Tag tag);
    // 根据id删除tag
    @Delete("delete from tags where id = #{id}")
    public int deleteTagById(Integer id);
    // 插入tag
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into tags(name,description) values (#{name},#{description})")
    public int insertTag(Tag tag);
    // 根据id更行tag
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

