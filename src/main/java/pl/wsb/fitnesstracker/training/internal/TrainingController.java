package pl.wsb.fitnesstracker.training.internal;

import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trainings")

class TrainingController {
    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;

    public TrainingController(
            TrainingService trainingService,
            TrainingMapper trainingMapper
    ) {
        this.trainingService = trainingService;
        this.trainingMapper = trainingMapper;
    }

    /**
     * Retrieves all trainings.
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all trainings for a specific user.
     */
    @GetMapping("{userId}")
    public List<TrainingDto> getAllTrainingsForDedicatedUser(@PathVariable Long userId) {
        return trainingService.getAllTrainingsForDedicatedUser(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}
