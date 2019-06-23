package org.nitin.mailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class Mailer {

    @Autowired
    private JavaMailSender sender;

    @Value("#{'${mail.recipients}'.split(',')}")
    private String[] recipients;

    @Value("${mail.body}")
    private String mailBody;

    @Value("${mail.subject}")
    private String mailSubject;

    @Value("${mail.attachment.name}")
    private String mailAttachmentName;

    public void sendEmail(String attachmentName) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setTo(recipients);
        helper.setText(mailBody);
        helper.setSubject(mailSubject);
        //ClassPathResource file = new ClassPathResource(attachmentName);
        File file = new File(attachmentName);
        helper.addAttachment(mailAttachmentName, file);
        sender.send(message);
        System.out.println("mailed");
    }
}
