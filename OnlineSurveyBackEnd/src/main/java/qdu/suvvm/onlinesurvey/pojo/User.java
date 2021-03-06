package qdu.suvvm.onlinesurvey.pojo;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: SUVVM
 * @Date: 2019/11/12 16:43
 */
@Component
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String pnum;
    private String email;
    private String gender;
    private String avatar;
    private String imgbase64;
    private Integer power;

    private Company company;
    private List<Tag> tags;
    private List<Investigate> investigates;
    private List<Result> results;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImgbase64() {
        return imgbase64;
    }

    public void setImgbase64(String imgbase64) {
        this.imgbase64 = imgbase64;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Company getCompany() { return company; }

    public void setCompany(Company company) { this.company = company; }

    public List<Tag> getTags() { return tags; }

    public void setTags(List<Tag> tags) { this.tags = tags; }

    public List<Investigate> getInvestigates() { return investigates; }

    public void setInvestigates(List<Investigate> investigates) { this.investigates = investigates; }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void reSetUser(Integer id, String username, String password, String name, String pnum, String email,
                          String gender, String avatar, String imgbase64, Integer power, Company company) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.pnum = pnum;
        this.email = email;
        this.gender = gender;
        this.avatar = avatar;
        this.imgbase64 = imgbase64;
        this.power = power;
        this.company = company;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", pnum='" + pnum + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                ", imgbase64='" + imgbase64 + '\'' +
                ", power=" + power +
                ", company=" + company +
                ", tags=" + tags +
                ", investigates=" + investigates +
                ", results=" + results +
                '}';
    }
}
