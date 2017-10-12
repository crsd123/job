package com.conanli.job.quartz;

import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class QuartzJobFactory extends AdaptableJobFactory {

    private Map<String, Pair<JobKey, Object>> jobs = new HashMap<>();
    private AbstractApplicationContext applicationContext;

    public QuartzJobFactory(AbstractApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object job = null;
        synchronized (jobs) {
            Pair<JobKey, Object> instance = jobs.get(bundle.getJobDetail().getKey().toString());
            if (instance == null || instance.getKey() != bundle.getJobDetail().getKey()) {
                Class<? extends Job> jobClass = bundle.getJobDetail().getJobClass();
                if (jobClass.isAnnotationPresent(Component.class)) {
                    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
                    context.setClassLoader(jobClass.getClassLoader());
                    if (applicationContext != null) {
                        context.setEnvironment(applicationContext.getEnvironment());
                        context.getBeanFactory().setParentBeanFactory(applicationContext.getBeanFactory());
                    }
                    context.register(jobClass);
                    context.refresh();
                    instance = new Pair<>(bundle.getJobDetail().getKey(), context.getBean(jobClass));
                    context.destroy();
                } else {
                    instance = new Pair<>(bundle.getJobDetail().getKey(), jobClass.newInstance());
                }
                jobs.put(bundle.getJobDetail().getKey().toString(), instance);
            }
            job = instance.getValue();
        }
        return job;
    }

    private static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
