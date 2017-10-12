package com.conanli.job.test;

import com.conanli.job.quartz.QuartzConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@Import({
        QuartzConfiguration.class
})
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        SpringJobApplication.class,
        SpringBootJobApplication.class,
        SpringCloudJobApplication.class
}))
public class JavaJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaJobApplication.class, args);
    }
}
