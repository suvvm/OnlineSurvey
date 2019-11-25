package qdu.suvvm.onlinesurvey.pojo;

import java.util.Date;

/**
 * @Author: SUVVM
 * @Date: 2019/11/12 16:54
 */
public class Investigate {
    private Integer id;
    private String name;
    private Boolean visible;
    private String description;
    private String details;
    private User owner;
    private Integer number;
    private Date time;

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

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Investigate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", visible=" + visible +
                ", description='" + description + '\'' +
                ", details='" + details + '\'' +
                ", owner=" + owner +
                ", number=" + number +
                ", time=" + time +
                '}';
    }
}
