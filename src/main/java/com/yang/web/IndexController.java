package com.yang.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/2/7.
 */
@Controller
public class IndexController {
    @Autowired
    private CounterService counterService;

    @ResponseBody
    @RequestMapping("/index")
    public String index() {
        counterService.increment("yang.index.count");
        return "Helloword";
    }

    boolean flag = false;

    @ResponseBody
    @RequestMapping("/start")
    public String start() {
        flag = true;
        new Thread(() -> {
            while (true) {
                if (flag) {
                    increment("yang.index.count");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else break;
            }
        }).start();
        return "start";
    }



    @ResponseBody
    @RequestMapping("/stop")
    public String stop() {
        flag = false;
        return "stop";
    }


    public void increment(String metricName) {
        counterService.increment(metricName);
    }


}
