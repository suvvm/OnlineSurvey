package qdu.suvvm.onlinesurvey.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import qdu.suvvm.onlinesurvey.pojo.Company;

import java.util.List;

/**
 * @ClassName: CmpMapper
 * @Description: dao层 针对companies的数据访问方法
 * @Author: SUVVM
 * @Date: 2019/11/14 23:48
 */
public interface CmpMapper {
    // 根据动态sql查询company
    @SelectProvider(type = CmpMapperProvider.class, method="findCmp")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "owner", column = "id", one = @One(select = "qdu.suvvm.onlinesurvey.mapper.UserMapper.getUserByCompanyId"))
    })
    public List<Company> getCompanies(Company cmp);

    // 根据id删除company
    @Delete("delete from companies where id=#{id}")
    public int deleteCmpById(Integer id);

    // 插入user并获取自增id
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into companies(name,description,forms,domain,ownerid) values(#{name},#{description},#{forms},#{domain},#{owner.id})")
    public int insertCmp(Company cmp);

    // 根据id更新company
    @UpdateProvider(type = CmpMapperProvider.class, method = "updateCmp")
    public int updateCmp(Company cmp);

    @Select("select * from companies where ownerid = #{id}")
    public Company getCompanyByUserId(Integer id);

    /**
     * @ClassName: CmpMapperProvider
     * @Description: company动态sql的Provider类
     * @Author: SUVVM
     * @Date: 2019/11/14 23:48
     */
    class CmpMapperProvider {
        /**
         * @FunctionName: findCmp
         * @Description: 用于生成查询company动态sql
         * @Parameter:
         *  cmp 查询数据封装的公司类
         * @Return: 返回对应sql语句
         */
        public String findCmp(Company cmp) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("companies");
                    if(cmp.getId() != null) {
                        WHERE("id = #{id}");
                    }
                    if(cmp.getName() != null) {
                        WHERE("name = #{name}");
                    }
                    if(cmp.getOwner() != null) {
                        WHERE("ownerid = #{owner.id}");
                    }
                }
            }.toString();
        }
        /**
         * @FunctionName: updateCmp
         * @Description: 用于生成更新company动态sql
         * @Parameter:
         *  cmp 更新数据封装的公司类
         * @Return: 返回对应sql语句
         */
        public String updateCmp(Company cmp) {
            return new SQL() {
                {
                    UPDATE("companies");
                    if(cmp.getName() != null) {
                        SET("name = #{name}");
                    }
                    if(cmp.getDescription() != null) {
                        SET("description = #{description}");
                    }
                    if(cmp.getForms() != null) {
                        SET("forms = #{forms}");
                    }
                    if(cmp.getDomain() != null) {
                        SET("domain = #{domain}");
                    }
                    if(cmp.getOwner() != null) {
                        SET("ownerid = #{owner.id}");
                    }
                    WHERE("id = #{id}");
                }
            }.toString();
        }
    }

}
