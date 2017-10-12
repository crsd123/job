package com.conanli.job;

import com.conanli.job.common.BusinessException;
import com.conanli.job.common.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                try (PrintWriter out = response.getWriter()) {
                    Response<Void> res = null;
                    if (e instanceof BusinessException) {
                        res = Response.failure(e.getMessage());
                    } else if (e instanceof HttpRequestMethodNotSupportedException) {
                        res = Response.failure(HttpStatus.METHOD_NOT_ALLOWED.value(), String.format("不支持 %s 方式请求", ((HttpRequestMethodNotSupportedException) e).getMethod()));
                    } else if (e instanceof HttpMediaTypeNotAcceptableException) {
                        res = Response.failure(HttpStatus.NOT_ACCEPTABLE.value(), "不接受此内容类型");
                    }  else if (e instanceof HttpMediaTypeNotSupportedException) {
                        res = Response.failure(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), String.format("不支持 %s 内容类型", ((HttpMediaTypeNotSupportedException) e).getContentType().getType()));
                    } else {
                        res = Response.failure("服务器异常");
                        e.printStackTrace();
                    }
                    out.write(objectMapper.writeValueAsString(res));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return new ModelAndView();
            }
        });
    }
}
