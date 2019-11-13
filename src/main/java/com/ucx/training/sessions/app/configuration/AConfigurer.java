package com.ucx.training.sessions.app.configuration;

import com.ucx.training.sessions.app.configuration.A;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AConfigurer {
    @Bean
    public A a(){
        return new A();
    }
}
