package co.edu.uco.asisteuco.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("co.edu.uco.asisteuco.application.outputport.repository")
@EntityScan("co.edu.uco.asisteuco.application.outputport.entity")
public class AsisteucoMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsisteucoMsApplication.class, args);
    }

}