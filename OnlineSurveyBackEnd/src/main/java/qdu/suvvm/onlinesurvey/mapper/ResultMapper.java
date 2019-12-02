package qdu.suvvm.onlinesurvey.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import qdu.suvvm.onlinesurvey.pojo.Result;

import java.util.List;

/**
 * @ClassName: ResultMapper
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2019/12/2 18:32
 */
public interface ResultMapper {

    @SelectProvider(type = ResultMapperProvider.class, method = "findRes")
    public List<Result> getResult(Result res);

    @Delete("delete from userresults where uid=#{uid}")
    public int deleteResultByUId(Integer uid);

    @Delete("delete from userresults where iid=#{iid}")
    public int deleteResultByIId(Integer iid);

    // 插入investigate并获取自增id
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into userresults(uid,iid,ans) values(#{uid},#{iid},#{ans})")
    public int insertResult(Result res);

    // 根据id更新investigate
    @Update("update from userresults set ans=#{ans} where iid=#{iid} && uid=##{uid}")
    public int updateResult(Result res);

    @Select("select * from userresults where uid=#{id}")
    public List<Result> getResultByUId(Integer id);

    @Select("select * from userresults where iid=#{id}")
    public List<Result> getResultByIId(Integer id);

    class ResultMapperProvider{
        /**
         * @FunctionName: findRes
         * @Description: 用于生成查询作答的动态sql
         * @Parameter:
         *  res 查询数据封装的回答类
         * @Return: 返回对应sql语句
         */
        public String findRes(Result res) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("userresults");
                    if(res.getUid() != null) {
                        WHERE("uid = #{uid}");
                    }
                    if(res.getIid() != null) {
                        WHERE("iid=#{iid}");
                    }
                }
            }.toString();
        }
    }
}
