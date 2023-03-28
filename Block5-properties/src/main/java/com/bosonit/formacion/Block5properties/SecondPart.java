package com.bosonit.formacion.Block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

@Configuration
public class SecondPart implements CommandLineRunner {

    @Value("${greeting}")
    private String greeting;
    @Value("${my.number}")
    private String myNumber;
    @Value("${new.property:new.property no tiene valor}")
    private String newProperty;

    public static void main(String[] args) {
        SpringApplication.run(SecondPart.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("El valor de greeting es: "+greeting);
        System.out.println("El valor de myNumber es: "+myNumber);
        System.out.println("El valor de newProperty es: "+newProperty);

    }
}
