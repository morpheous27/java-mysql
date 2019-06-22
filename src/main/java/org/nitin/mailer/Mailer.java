package org.nitin.mailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class Mailer {

    @Autowired
    private JavaMailSender sender;

    public void sendEmail() throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setTo("nitinsaxena27@gmail.com");
        helper.setText("Report is attachment.");
        helper.setSubject("MNR-PORTAL-REPORT");
        ClassPathResource file = new ClassPathResource("reports46.txt");
        helper.addAttachment("MNR_REPORT.txt", file);
        sender.send(message);
    }
}
