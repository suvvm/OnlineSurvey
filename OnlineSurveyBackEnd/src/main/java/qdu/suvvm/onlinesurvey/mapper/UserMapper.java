package qdu.suvvm.onlinesurvey.mapper;

import javafx.beans.binding.When;
import jdk.nashorn.internal.objects.annotations.Where;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import qdu.suvvm.onlinesurvey.pojo.User;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @Description: dao层 针对users的数据访问方法
 * @Author: SUVVM
 * @Date: 2019/11/14 20:46
 */
public interface UserMapper {
    // 根据动态sql查询user
    @SelectProvider(type = UserMapperProvider.class, method="findUser")
    public List<User> getUser(User user);
    // 根据id删除user
    @Delete("delete from users where id=#{id}")
    public int deleteUserById(Integer id);
    // 插入user并获取自增id
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into user(username,password,name,pnum,email,gender,avatar,imgbase64,power) values(#{username},#{password},#{name},#{pnum},#{email},#{gender},#{avatar},#{imgbase64},#{power})")
    public int insertUser(User user);
    // 根据id更新user
    @UpdateProvider(type = UserMapperProvider.class, method = "updateUser")
    public int updateUserById(Integer id);
    /**
     * @ClassName: UserMapperProvider
     * @Description: user动态sql的Provider类
     * @Author: SUVVM
     * @Date: 2019/11/14 20:46
     */
    class UserMapperProvider {
        /**
         * @FunctionName: findUser
         * @Description: 用于生成查询用户动态sql
         * @Parameter:
         *  user 查询数据封装的用户类
         * @Return: 返回对应的sql语句
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
        /**
         * @FunctionName: updateUser
         * @Description: 用于生成更新用户动态sql
         * @Parameter:
         *  user 需更新数据封装的用户类
         * @Return: 返回对应sql语句
         */
        public String updateUser(User user) {
            return new SQL() {
                {
                    UPDATE("users");
                    if(user.getUsername() != null) {
                        SET("username = #{username}");
                    }
                    if(user.getPassword() != null) {
                        SET("password = #{password}");
                    }
                    if(user.getEmail() != null) {
                        SET("email = #{email}");
                    }
                    if(user.getPnum() != null) {
                        SET("pnum = #{pnum}");
                    }
                    if(user.getGender() != null) {
                        SET("gender= #{gender}");
                    }
                    if(user.getName() != null) {
                        SET("name = #{name}");
                    }
                    if(user.getAvatar() != null) {
                        SET("avatar = #{avatar}");
                    }
                    if(user.getImgbase64() != null) {
                        SET("imgbase64 = #{imgbase64}");
                    }
                    if(user.getPower() != null) {
                        SET("power = #{power}");
                    }
                    WHERE("id = #{id}");
                }
            }.toString();
        }
    }

}
