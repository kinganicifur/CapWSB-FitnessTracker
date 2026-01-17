package pl.wsb.fitnesstracker.training.api;

import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on ID.
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves all trainings.
     */
    List<Training> findAllTrainings();
}