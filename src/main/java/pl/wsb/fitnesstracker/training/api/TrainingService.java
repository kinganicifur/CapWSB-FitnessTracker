package pl.wsb.fitnesstracker.training.api;

import java.util.List;

/**
 * Interface for Training Service.
 * Provides business logic operations for training management.
 */
public interface TrainingService {

    /**
     * Retrieves all trainings.
     *
     * @return {@link List} of all trainings
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves all trainings for a specific user.
     *
     * @param userId ID of the user whose trainings to retrieve
     * @return {@link List} of trainings belonging to the specified user
     */
    List<Training> getAllTrainingsForDedicatedUser(Long userId);
}