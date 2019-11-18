package qdu.suvvm.onlinesurvey;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import qdu.suvvm.onlinesurvey.mapper.TagMapper;
import qdu.suvvm.onlinesurvey.pojo.Tag;

import java.util.List;

@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootTest
class OnlinesurveyApplicationTests {

    @Autowired
    private TagMapper tagMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void select() {
        Tag t = new Tag();
        // t.setId(400000001);
        t.setName("秃头");
        List<Tag> tags = tagMapper.selectTags(t);
        for(Tag tag : tags){
            System.out.println(tag.getId() + " " + tag.getName() + " " + tag.getDescription());
        }
    }

}
