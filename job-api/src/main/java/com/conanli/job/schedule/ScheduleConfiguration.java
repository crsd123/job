package com.conanli.job.schedule;

import com.conanli.job.contact.ContactStore;
import com.conanli.job.quartz.QuartzManager;
import com.conanli.job.schedule.appender.Log4jAppender;
import com.conanli.job.schedule.appender.LogbackAppender;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

public class ScheduleConfiguration {

    @Bean
    public ContactStore contactStore(DSLContext dsl) {
        return new ContactStore(dsl);
    }

    @Bean
    public ScheduleStore scheduleStore(DSLContext dsl) {
        return new ScheduleStore(dsl);
    }

    @Bean
    public ScheduleLoader scheduleLoader() {
        return new ScheduleLoader();
    }

    @Bean
    public ScheduleAppender scheduleAppender() {
        ScheduleAppender scheduleAppender = new ScheduleAppender();
        Log4jAppender.regist(scheduleAppender);
        LogbackAppender.regist(scheduleAppender);
        return scheduleAppender;
    }

    @Bean
    public ScheduleAlerter scheduleAlerter(JavaMailSender mailSender, @Value("${spring.mail.username}") String mail) {
        ScheduleAlerter scheduleAlerter = new ScheduleAlerter();
        scheduleAlerter.setMail(mail);
        scheduleAlerter.setMailSender(mailSender);
        return scheduleAlerter;
    }

    @Bean
    public ScheduleListener scheduleListener(ScheduleAppender scheduleAppender, ScheduleStore scheduleStore, ContactStore contactStore, ScheduleAlerter scheduleAlerter) {
        ScheduleListener scheduleListener = new ScheduleListener();
        scheduleListener.setScheduleAppender(scheduleAppender);
        scheduleListener.setScheduleStore(scheduleStore);
        scheduleListener.setContactStore(contactStore);
        scheduleListener.setScheduleAlerter(scheduleAlerter);
        return scheduleListener;
    }

    @Bean
    public ScheduleManager scheduleManager(QuartzManager quartzManager, ScheduleStore scheduleStore, ScheduleLoader scheduleLoader) {
        ScheduleManager scheduleManager = new ScheduleManager(quartzManager, scheduleStore, scheduleLoader);
        scheduleManager.init();
        return scheduleManager;
    }
}
