package org.example.userauthservice.controller;

import org.example.userauthservice.model.DeleteUserDTO;
import org.springframework.amqp.rabbit.core.RabbitMessageOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final RabbitMessageOperations rabbitTemplate;

    public TestController(RabbitMessageOperations rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        rabbitTemplate.convertAndSend("myQueue", "Hello, world!");
        return "user";
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        rabbitTemplate.convertSendAndReceive("myQueue", new DeleteUserDTO(userId), DeleteUserDTO.class);
        return "user to delete: " + userId;
    }
}
