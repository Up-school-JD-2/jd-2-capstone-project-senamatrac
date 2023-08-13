package io.upschool;

import io.upschool.enums.LegType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;

@SpringBootApplication
@EnableEnversRepositories
public class CapstoneProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapstoneProjectApplication.class, args);

        System.out.println(LegType.DOMESTIC.ordinal());
    }

}