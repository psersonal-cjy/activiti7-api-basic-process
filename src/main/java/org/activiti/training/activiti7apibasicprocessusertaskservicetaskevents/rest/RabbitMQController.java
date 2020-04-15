package org.activiti.training.activiti7apibasicprocessusertaskservicetaskevents.rest;

import org.slf4j.Logger;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/mq-send")
    public String sendMessage(@RequestParam(value = "message") String message) {
        this.rabbitTemplate.convertAndSend("hello", message);
        return "Send Message Success:" +message;
    }
}
