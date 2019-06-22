package org.nitin.scheduler;

import org.nitin.model.Report;
import org.nitin.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchedulerJob {

    @Autowired
    private ReportRepository reportRepository;

    @Scheduled(cron = "${db.cron}")
    public List<Report> generateWeeklyReportSchedule() {
        System.out.println(reportRepository.getReport());
        return null;
    }
}
