package com.ywh.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * CreateTime: 2019-03-26 17:43
 * ClassName: HelloService
 * Package: com.ywh.serviceribbon.service
 * Describe:
 * 使用ribbon调用客户端的业务层
 *
 * @author YWH
 */
@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name){
        return restTemplate.getForObject("http://EUREKA-HI/hi?name=" + name,String.class);
    }


    public String hiError(String name){
        return "hi ," + name + ", sorry , error";
    }

}
