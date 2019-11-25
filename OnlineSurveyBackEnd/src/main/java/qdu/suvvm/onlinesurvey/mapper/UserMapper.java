package qdu.suvvm.onlinesurvey.mapper;

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
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "tags", column = "id", many = @Many(select = "qdu.suvvm.onlinesurvey.mapper.TagMapper.getTagByUserId")),
            @Result(property = "company", column = "id", one = @One(select = "qdu.suvvm.onlinesurvey.mapper.CmpMapper.getCompanyByUserId")),
            @Result(property = "investigates", column = "id", many = @Many(select = "qdu.suvvm.onlinesurvey.mapper.InvMapper.getInvByUserId"))
    })
    public List<User> getUser(User user);

    // 根据tag id查询用户
    @Select("select * from users u inner join usertag ut on u.id=ut.uid where ut.tid=#{tid}")
    public User getUserByTagId(Integer tid);

    // 根据id删除user
    @Delete("delete from users where id=#{id}")
    public int deleteUserById(Integer id);

    // 插入user并获取自增id
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into users(username,password,name,pnum,email,gender,avatar,imgbase64,power) values(#{username},#{password},#{name},#{pnum},#{email},#{gender},#{avatar},#{imgbase64},#{power})")
    public int insertUser(User user);

    // 根据id更新user
    @UpdateProvider(type = UserMapperProvider.class, method = "updateUser")
    public int updateUserById(User user);

    // 添加user与tag关系
    @Insert("insert into usertag values(#{uid},#{tid})")
    public int insertUserTag(Integer uid, Integer tid);

    // 根据公司id查询用户
    @Select("select * from users where cmp_id=#{id}")
    public User getUserByCompanyId(Integer id);

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
                    FROM("users");
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
                    if(user.getCompany() != null) {
                        WHERE("cmp_id = #{company.id}");
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
                    if(user.getCompany() != null) {
                        SET("cmp_id = #{company.id}");
                    }
                    WHERE("id = #{id}");
                }
            }.toString();
        }
    }

}
