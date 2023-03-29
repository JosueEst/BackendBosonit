package com.bosonit.formacion.block5profiles;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationP {

    @Value("${active:EMPTY}")
    private String enviroment;
    @Value("${bd.url:EMPTY}")
    private String url;

    public ConfigurationP() {
    }
    @Bean
    public ConfigurationP c(){
        return new ConfigurationP();
    }

    public String getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(String enviroment) {
        this.enviroment = enviroment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
