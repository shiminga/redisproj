package com.producer.controller;

import com.producer.service.impl.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;

@Controller
@ResponseBody
public class KafkaSendController {

    @Autowired
    KafkaSender kafkaSender;

    @GetMapping("/send/{msg}")
    public String sendMsg(@PathVariable("msg") String msg) {
        try {
            kafkaSender.send(msg);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "成功";
    }
}
