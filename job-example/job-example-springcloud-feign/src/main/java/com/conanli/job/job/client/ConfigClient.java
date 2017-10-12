package com.conanli.job.job.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "test", url = "http://localhost:9898")
public interface ConfigClient {

    @RequestMapping(path = "/hello/say", method = RequestMethod.POST)
    String getConfig();
}
