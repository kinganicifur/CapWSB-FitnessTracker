package pl.wsb.fitnesstracker.training.internal;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TrainingWeeklyReportScheduler {
    private final TrainingWeeklyReportService reportService;

    TrainingWeeklyReportScheduler(TrainingWeeklyReportService reportService) {
        this.reportService = reportService;
    }

    // Run every Monday at 8:00
    @Scheduled(cron = "0 0 8 * * MON")
    //For testing purpose
    //@Scheduled(cron = "*/30 * * * * *")


    void generateWeeklyReport() {
        System.out.println("-----Scheduler triggered-----");
        reportService.generateWeeklyReport();
        reportService.sendTotalTrainingsReport();
    }
}
