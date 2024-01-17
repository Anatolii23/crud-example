package config;

import com.github.database.rider.spring.api.DBRider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Database Test Configuration used junit 5 and database rider.
 *
 * @author Anatolii Hamza
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@DBRider
@ContextConfiguration(classes = DbTestApplication.class)
public @interface DbTestConfiguration {
}
