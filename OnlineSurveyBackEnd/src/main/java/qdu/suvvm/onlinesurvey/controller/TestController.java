package qdu.suvvm.onlinesurvey.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: Controller层 与前端数据交互测试 接收text1与text2并打印 之后返回json yes
 * @Author: SUVVM
 * @Date: 2019/11/12 21:45
 */
@RestController
public class TestController {
    @PostMapping(value = "/test")
    public String test(@RequestParam("text1") String text1, @RequestParam("text2") String text2) {
        System.out.println(text1 + " " + text2);
        return "yes";
    }
}
