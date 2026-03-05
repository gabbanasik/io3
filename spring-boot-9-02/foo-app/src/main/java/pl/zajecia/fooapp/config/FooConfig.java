package pl.zajecia.fooapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FooConfig {
    @Bean
    String bar(){return "bar";}
}
