package com.leyou;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;  //一定要引用通用mapper

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.leyou.item.mapper")
public class LeyouItemServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(LeyouItemServiceApplication.class, args);
    }
}
