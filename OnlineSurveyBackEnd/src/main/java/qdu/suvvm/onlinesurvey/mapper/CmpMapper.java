package qdu.suvvm.onlinesurvey.mapper;

import org.apache.ibatis.annotations.SelectProvider;
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
    public List<Company> getCompanies(Company cmp);

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
         * @Return: 返回对应的查询语句
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
    }
}
