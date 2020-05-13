package qdu.suvvm.onlinesurvey.pojo;

import org.springframework.stereotype.Component;

/**
 * @Author: SUVVM
 * @Date: 2019/11/12 16:52
 */
@Component
public class Company {
    private Integer id;
    private String name;
    private String description;
    private String forms;
    private String domain;
    private User owner;

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

    public String getForms() {
        return forms;
    }

    public void setForms(String forms) {
        this.forms = forms;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void reSetCompany(Integer id, String name, String description, String forms, String domain, User owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.forms = forms;
        this.domain = domain;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", forms='" + forms + '\'' +
                ", domain='" + domain + '\'' +
                ", owner=" + owner +
                '}';
    }
}
