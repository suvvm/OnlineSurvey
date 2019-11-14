package qdu.suvvm.onlinesurvey;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = "qdu.suvvm.onlinesurvey.mapper")
@SpringBootApplication
public class OnlinesurveyApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinesurveyApplication.class, args);
    }

}
