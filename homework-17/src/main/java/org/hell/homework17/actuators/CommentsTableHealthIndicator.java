package org.hell.homework17.actuators;

import org.hell.homework17.repository.CommentRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class CommentsTableHealthIndicator implements HealthIndicator {

    //Indicator looks artificial but there's hardly anything to check in the application at this timme

    private final CommentRepository repository;

    public CommentsTableHealthIndicator(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Health health() {
        long count = repository.count();

        if (count < 1000000) {
            return Health.up()
                    .status(Status.UP)
                    .withDetail("message", "Everything is fine.")
                    .build();
        } else {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "It's time to make something about performance")
                    .build();
        }
    }
}
