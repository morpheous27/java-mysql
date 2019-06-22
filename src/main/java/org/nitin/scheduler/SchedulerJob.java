package org.nitin.scheduler;

import org.nitin.filewriter.TextWriter;
import org.nitin.mailer.Mailer;
import org.nitin.model.Report;
import org.nitin.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SchedulerJob {

    @Autowired
    private TextWriter<Report> fileWriter;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private Mailer mailer;

    @Scheduled(cron = "${db.cron}")
    public void generateWeeklyReportSchedule() throws Exception {

        List<Report> reports = reportRepository.getReport();
        if (reports != null) {

            fileWriter.writeDataUsingFiles(reports);
        }
        mailer.sendEmail();
    }
}
