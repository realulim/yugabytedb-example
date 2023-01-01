package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"example"})
public class YugabyteApplication {

    public static void main(String[] args) {
        System.out.println("ENV: " + System.getenv("JAVA_HOME"));
        SpringApplication.run(YugabyteApplication.class, args);
    }

}
