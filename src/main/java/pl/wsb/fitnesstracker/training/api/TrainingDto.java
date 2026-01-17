package pl.wsb.fitnesstracker.training.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

@Data
@AllArgsConstructor
public class TrainingDto {
    private Long id;
    private UserDto user;
    private Date startTime;
    private Date endTime;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;
    }
