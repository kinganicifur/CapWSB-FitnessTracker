package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrainingWeeklyReportService {
    private final TrainingProvider trainingProvider;
    private final EmailSender emailSender;

    TrainingWeeklyReportService(TrainingProvider trainingProvider, EmailSender emailSender) {
        this.trainingProvider = trainingProvider;
        this.emailSender = emailSender;
    }

    public void generateWeeklyReport() {
        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.minusWeeks(1).with(DayOfWeek.MONDAY);
        LocalDate weekEnd = weekStart.plusDays(6);

        List<Training> trainings = trainingProvider.findAllTrainings();

        Map<User, List<Training>> trainingsPerUser =
                trainings.stream()
                        //For testing purpose this line need to be commented because we don't have any data
                        .filter(training -> isInWeek(training, weekStart, weekEnd))
                        .collect(Collectors.groupingBy(Training::getUser));

        trainingsPerUser.forEach(this::printUserReport);
    }

    private boolean isInWeek(Training training, LocalDate start, LocalDate end) {
        LocalDate trainingDate =
                training.getStartTime()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

        return !trainingDate.isBefore(start) && !trainingDate.isAfter(end);
    }

    private void printUserReport(User user, List<Training> trainings) {
        System.out.println("-------------------");
        System.out.println("Weekly user report:");
        System.out.println(user.getFirstName() + " " + user.getLastName());
        System.out.println("Number of trainings: " + trainings.size());

        double totalDistance = trainings.stream()
                .mapToDouble(Training::getDistance)
                .sum();

        System.out.println("Summary distance: " + totalDistance + " km");
    }

    //Calculate total trainings report
    public void sendTotalTrainingsReport() {
        List<Training> trainings = trainingProvider.findAllTrainings();

        Map<User, Long> trainingsCountPerUser =
                trainings.stream()
                        .collect(Collectors.groupingBy(
                                Training::getUser,
                                Collectors.counting()
                        ));

        trainingsCountPerUser.forEach(this::sendEmail);
    }

    private void sendEmail(User user, Long trainingsCount) {
        EmailDto email = new EmailDto(
                user.getEmail(),
                "fitnesstracker@cap.wsb.com",
                "Your training summary",
                """
                Hello %s,
    
                You have %d trainings registered in FitnessTracker.
    
                Best regards,
                FitnessTracker Team
                """.formatted(
                        user.getFirstName(),
                        trainingsCount
                )
        );

        emailSender.send(email);
    }

}
