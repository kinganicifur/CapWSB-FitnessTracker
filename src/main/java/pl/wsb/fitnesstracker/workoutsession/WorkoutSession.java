package pl.wsb.fitnesstracker.workoutsession;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.training.api.Training;

@Entity
@Table(name = "Workout_Session")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id")
    private Training trainingId;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "startLatitude")
    private double startLatitude;

    @Column(name = "startLongitude")
    private double startLongitude;

    @Column(name = "endLatitude")
    private double endLatitude;

    @Column(name = "endLongitude")
    private double endLongitude;

    @Column(name = "altitude")
    private double altitude;
}
