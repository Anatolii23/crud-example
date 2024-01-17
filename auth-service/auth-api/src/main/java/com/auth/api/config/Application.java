package com.auth.api.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.time.ZoneOffset;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Application Config.
 *
 * @author Anatolii Hamza
 */
@SpringBootApplication(scanBasePackages = "com.auth")
public class Application {

    protected Application() {
    }

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
        Locale.setDefault(Locale.ENGLISH);
        new SpringApplicationBuilder(Application.class).run(args);
    }
}