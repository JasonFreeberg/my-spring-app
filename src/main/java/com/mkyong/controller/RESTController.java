package com.mkyong.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Enumeration;

@RestController
public class RESTController {
    @GetMapping("/health")
    public Map<String, String> health(HttpServletRequest request) {
        Date date = new Date();
        HashMap<String, String> map = new HashMap<>();
        
        map.put("requestTime", date.toString());
        map.put("scheme", request.getScheme());
        map.put("port", String.valueOf(request.getLocalPort()));
        map.put("URL", request.getRequestURL().toString());

        System.out.println("** Request received on /health at "+map.get("requestTime")+" over "+map.get("scheme")+" on port "+map.get("port"));
        System.out.println("Full URL: "+map.get("URL"));

        System.out.println("Headers:");
        for (Enumeration<String> e = request.getHeaderNames(); e.hasMoreElements();) {
            String headerName = (String) e.nextElement();
            String headerValue = request.getHeader(headerName);

            System.out.println("\t"+headerName+" = "+headerValue);
        }
        return map;
    }
}
