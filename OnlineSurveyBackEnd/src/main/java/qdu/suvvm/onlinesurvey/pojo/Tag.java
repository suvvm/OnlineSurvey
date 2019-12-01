package qdu.suvvm.onlinesurvey.pojo;

import java.util.List;

/**
 * @Author: SUVVM
 * @Date: 2019/11/12 16:58
 */
public class Tag {
    private Integer id;
    private String name;
    private String description;
    private List<User> users;
    private List<Investigate> investigates;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Investigate> getInvestigates() {
        return investigates;
    }

    public void setInvestigates(List<Investigate> investigates) {
        this.investigates = investigates;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                ", investigates=" + investigates +
                '}';
    }
}
