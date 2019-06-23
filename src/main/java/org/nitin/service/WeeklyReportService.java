package org.nitin.service;

import org.nitin.filewriter.TextFileWriter;
import org.nitin.mailer.Mailer;
import org.nitin.model.Report;
import org.nitin.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WeeklyReportService implements ReportService {

    @Autowired
    private TextFileWriter<Report> writer;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private Mailer mailer;

    @Override
    public boolean process() {
        boolean isSuccess = false;

        List<Report> reports = reportRepository.getReport();
        String fileName = "";
        if (reports != null) {
            fileName = writer.writeDataUsingFiles(reports);
        }
        try {
            mailer.sendEmail(fileName);
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }
}
