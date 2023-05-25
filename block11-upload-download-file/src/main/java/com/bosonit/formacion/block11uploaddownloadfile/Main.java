package com.bosonit.formacion.block11uploaddownloadfile;

import com.bosonit.formacion.block11uploaddownloadfile.storage.StorageProperties;
import com.bosonit.formacion.block11uploaddownloadfile.storage.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Main {


	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
