package pl.wsb.fitnesstracker.training.api;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.internal.UserMapper;

@Component
public class TrainingMapper {

    private final UserMapper userMapper;

    public TrainingMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }
}
