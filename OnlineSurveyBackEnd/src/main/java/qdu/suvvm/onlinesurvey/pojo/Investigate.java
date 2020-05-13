package qdu.suvvm.onlinesurvey.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author: SUVVM
 * @Date: 2019/11/12 16:54
 */
@Component
public class Investigate {
    private Integer id;
    private String name;
    private Boolean visible;
    private String description;
    private String details;
    private User owner;
    private Date time;
    private List<Tag> tags;
    private List<Result> results;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void reSetInvestigate(Integer id, String name, Boolean visible, String description, String details, User owner, Date time) {
        this.id = id;
        this.name = name;
        this.visible = visible;
        this.description = description;
        this.details = details;
        this.owner = owner;
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
                ", time=" + time +
                ", tags=" + tags +
                ", results=" + results +
                '}';
    }
}
