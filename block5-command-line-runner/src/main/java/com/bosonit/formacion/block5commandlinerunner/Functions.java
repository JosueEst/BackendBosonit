package com.bosonit.formacion.block5commandlinerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Functions {


    @Bean
    public String initialFun() throws Exception{
        return "Hola desde clase inicial";
    }

    @Bean
    public String secondFunction() throws Exception {
        return   "Hola desde clase secundaria";
    }
    @Bean
    public String thirdFunction() throws Exception {

        return initialFun()+" "+secondFunction() + " thirdFunction";
    }

}
