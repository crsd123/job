package com.conanli.job.controller;

import com.conanli.job.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Hello")
@RestController
@RequestMapping("/hello")
public class HelloController {

    @ApiOperation(value = "say", response = String.class)
    @PostMapping("/say")
    public Response<String> say() {
        return Response.success("Hello World!");
    }

}
