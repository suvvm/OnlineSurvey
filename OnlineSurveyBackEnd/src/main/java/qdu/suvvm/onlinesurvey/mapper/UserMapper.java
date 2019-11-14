package qdu.suvvm.onlinesurvey.mapper;

import javafx.beans.binding.When;
import jdk.nashorn.internal.objects.annotations.Where;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import qdu.suvvm.onlinesurvey.pojo.User;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @Description: dao层 针对users的数据访问
 * @Author: SUVVM
 * @Date: 2019/11/14 20:46
 */
public interface UserMapper {
    // 根据id查询user
    @SelectProvider(type = UserMapperProvider.class, method="findUser")
    public List<User> getUserbyId(Integer id);
    // 根据id删除user
    @Delete("delete from users where id=#{id}")
    public int deleteUserbyId(Integer id);
    // 根据插入user并获取自增id
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into user(username,password,name,pnum,email,gender,avatar,imgbase64,power) values{#{username},#{password},#{name},#{pnum},#{email},#{gender},#{avatar},#{imgbase64},#{power}}")
    public int insertUser(User user);


    /**
     * @ClassName: UserMapperProvider
     * @Description: user动态sql的Provider类
     * @Author: SUVVM
     * @Date: 2019/11/14 20:46
     */
    class UserMapperProvider {
        /**
         * @FunctionName: UserMapperProvider
         * @Description: 用于生成查询用户动态sql
         * @Parameter:
         *  user 查询数据封装的用户类
         * @Return: 返回对应的查询语句
         */
        public String findUser(User user) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("user");
                    if(user.getId() != null) {
                        WHERE("id = #{id}");
                    }
                    if(user.getUsername() != null) {
                        WHERE("username = #{username}");
                    }
                    if(user.getPassword() != null) {
                        WHERE("password = #{password}");
                    }
                    if(user.getName() != null) {
                        WHERE("name = #{name}");
                    }
                    if(user.getPnum() != null) {
                        WHERE("pnum = #{pnum}");
                    }
                    if(user.getEmail() != null) {
                        WHERE("email = #{email}");
                    }
                    if(user.getGender() != null) {
                        WHERE("gender = #{gender}");
                    }
                    if(user.getPower() != null) {
                        WHERE("power = #{power}");
                    }
                }
            }.toString();
        }

    }

}
