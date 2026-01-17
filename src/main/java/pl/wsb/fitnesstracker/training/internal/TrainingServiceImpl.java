package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.*;

import java.util.List;
import java.util.Optional;

@Service
class TrainingServiceImpl implements TrainingService, TrainingProvider {

    private final TrainingRepository trainingRepository;

    TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Optional<Training> getTraining(Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getAllTrainingsForDedicatedUser(Long userId) {
        return trainingRepository.getTrainingsByUser(userId);
    }

    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }
}
