package pl.perfumeria.perfumery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PerfumeryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerfumeryApplication.class, args);
    }

}
