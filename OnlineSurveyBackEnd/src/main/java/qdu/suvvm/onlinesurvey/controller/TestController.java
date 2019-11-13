package qdu.suvvm.onlinesurvey.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: SUVVM
 * @Date: 2019/11/13 13:54
 */
@RestController
public class TestController {
    @PostMapping(value = "/test")
    public String test(@RequestParam("text1") String text1, @RequestParam("text2") String text2) {
        System.out.println(text1 + " " + text2);
        return "yes";
    }
}
