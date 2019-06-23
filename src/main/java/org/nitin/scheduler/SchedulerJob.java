package org.nitin.scheduler;

import org.nitin.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerJob {

    @Autowired
    private ReportService reportService;

    @Scheduled(cron = "${db.cron}")
    public void generateWeeklyReportSchedule() throws Exception {
        reportService.process();
    }
}
