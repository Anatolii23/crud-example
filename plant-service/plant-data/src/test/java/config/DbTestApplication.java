package config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Db Test Application.
 *
 * @author Anatolii Hamza
 */
@SpringBootApplication
@EntityScan(basePackages = "com.plant.data.entity")
@EnableJpaRepositories("com.plant.data.repository")
public class DbTestApplication {

    protected DbTestApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(DbTestApplication.class, args);
    }
}
