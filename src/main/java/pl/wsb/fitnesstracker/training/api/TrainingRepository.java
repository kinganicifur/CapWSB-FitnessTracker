package pl.wsb.fitnesstracker.training.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    default List<Training> getTrainingsByUser(Long userId) {
        return findAll().stream()
                .filter(training -> training.getUser() != null &&
                        Objects.equals(training.getUser().getId(), userId))
                .toList();
    }
}
