package com.vasilchenko.web.filter;

import com.vasilchenko.web.metric.MetricClass;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @autor Viacheslav Vasilchenko
 */

@Component
public class MetricFilter extends OncePerRequestFilter {

    public static List<MetricClass> metricClassList = new ArrayList<>();

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        long time = System.currentTimeMillis();
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        chain.doFilter(requestWrapper, responseWrapper);

        time = System.currentTimeMillis() - time;

        String responseBody = "";
        if (responseWrapper.getContentType().contains("UTF-8")) {
            responseBody = IOUtils.toString(responseWrapper.getContentInputStream(), String.valueOf(StandardCharsets.UTF_8));
        }

        metricClassList.add(new MetricClass(
                metricClassList.size() + 1,
                HttpStatus.valueOf(responseWrapper.getStatusCode()),
                requestWrapper.getRequestURI(),
                time,
                responseBody,
                responseBody.length()
        ));

        responseWrapper.copyBodyToResponse();
    }
}
