package com.conanli.job;

import com.conanli.job.jooq.JooqConfiguration;
import com.conanli.job.quartz.QuartzConfiguration;
import com.conanli.job.schedule.ScheduleConfiguration;
import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Import({
        JooqConfiguration.class,
        QuartzConfiguration.class,
        ScheduleConfiguration.class,
        WebMvcConfiguration.class
})
@SpringBootApplication
public class JobApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApiApplication.class, args);
    }

    @EnableSwagger2
    static class Swagger2Config {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder().title("Job").version("0.0.1").build();
        }
    }
}
