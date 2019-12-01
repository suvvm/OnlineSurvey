package qdu.suvvm.onlinesurvey.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import qdu.suvvm.onlinesurvey.pojo.Investigate;
import qdu.suvvm.onlinesurvey.pojo.Tag;

import java.util.List;

/**
 * @ClassName: InvMapper
 * @Description: 针对investigates的数据访问方法
 * @Author: SUVVM
 * @Date: 2019/11/21 21:38
 */
public interface InvMapper {

    // 根据动态sql查询investigate
    @SelectProvider(type = InvMapperProvider.class, method = "findInv")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "tags", column = "id", many = @Many(select = "qdu.suvvm.onlinesurvey.mapper.TagMapper.getTagByInvId"))
    })
    public List<Investigate> getInvestigate(Investigate inv);

    // 根据id删除investigate
    @Delete("delete from investigates where id=#{id}")
    public int deleteInvById(Integer id);

    // 插入investigate并获取自增id
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into investigates(name,visible,description,details,ownerid,time) values(#{name},#{visible},#{description},#{details},#{owner.id},#{time})")
    public int insertInvestigate(Investigate inv);

    // 根据id更新investigate
    @UpdateProvider(type = InvMapperProvider.class, method = "updateInv")
    public int updateInvestigate(Investigate inv);

    // 根据用户id查询问卷
    @Select("select * from investigates where ownerid = #{id}")
    public Investigate getInvByUserId(Integer id);

    // 根据Tagid查询问卷
    @Select("select * from investigates i inner join invtag it on i.id = it.iid where it.tid=#{tid}")
    public Investigate getInvByTagId(Integer tid);

    // 插入问卷tag映射关系
    @Insert("insert into invtag(iid,tid) values(#{inv.id},#{tag.id})")
    public int insertInvTag(Investigate inv, Tag tag);

    class InvMapperProvider{
        /**
         * @FunctionName: findInv
         * @Description: 用于生成查询问卷的动态sql
         * @Parameter:
         *  inv 查询数据封装的问卷类
         * @Return: 返回对应sql语句
         */
        public String findInv(Investigate inv) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("investigates");
                    if(inv.getId() != null) {
                        WHERE("id = #{id}");
                    }
                    if(inv.getName() != null) {
                        WHERE("name=#{name}");
                    }
                    if(inv.getOwner() != null) {
                        WHERE("ownerid=#{owner.id}");
                    }
                    if(inv.getVisible() != null) {
                        WHERE("visible=#{visible}");
                    }
                }
            }.toString();
        }
        /**
         * @FunctionName: updateInv
         * @Description: 用于生成更新问卷的动态sql
         * @Parameter:
         *  cmp 更新数据封装的问卷类
         * @Return: 返回对应sql语句
         */
        public String updateInv(Investigate inv) {
            return new SQL() {
                {
                    UPDATE("investigates");
                    if(inv.getName() != null) {
                        SET("name = #{name}");
                    }
                    if(inv.getDescription() != null) {
                        SET("description = #{description}");
                    }
                    if(inv.getVisible() != null) {
                        SET("visible = #{visible}");
                    }
                    if(inv.getDetails() != null) {
                        SET("details = #{details}");
                    }
                    if(inv.getTime() != null) {
                        SET("time = #{time}");
                    }
                    WHERE("id = #{id}");
                }
            }.toString();
        }
    }
}
