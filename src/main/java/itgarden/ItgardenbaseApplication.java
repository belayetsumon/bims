package itgarden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories(basePackages = {"itgarden.repository"})
//@EnableAdminServer
public class ItgardenbaseApplication {

    public static void main(String[] args) {
        
        SpringApplication.run(ItgardenbaseApplication.class, args);
    }

}
