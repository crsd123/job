package com.conanli.job.schedule;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleAlerter {

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String mail;
    private JavaMailSender mailSender;

    public void alert(String receiver, String subject, Throwable e) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail);
        mailMessage.setTo(receiver);
        mailMessage.setSubject(subject);
        mailMessage.setText(String.format("host: %s\ndate: %s\nmessage: %s\nexception:\n%s",
                getHost(), df.format(new Date()), e.getMessage(), getStackTrace(e)));
        mailSender.send(mailMessage);
    }

    public void alert(String receiver, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail);
        mailMessage.setTo(receiver);
        mailMessage.setSubject(subject);
        mailMessage.setText(String.format("host: %s\ndate: %s\ncontent:\n%s",
                getHost(), df.format(new Date()), content));
        mailSender.send(mailMessage);
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private String getHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}
