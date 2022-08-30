package org.example.restaurant.amqp;

import lombok.extern.slf4j.Slf4j;
import org.example.restaurant.dto.DeleteUserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeleteUserListener {
    @RabbitListener(queues = "myQueue")
    void deleteUser(DeleteUserDTO deleteUserDTO) {
        log.info("delete user " + deleteUserDTO);
    }

}
