package cn.nexura.judgeservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("cn.nexura.judgeservice")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("cn.nexura")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cn.nexura.serviceclient.service")
public class NexuraOjJudgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NexuraOjJudgeServiceApplication.class, args);
    }

}
