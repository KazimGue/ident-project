package project.post_ident;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PostIdentApplication {
    ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =SpringApplication.run(PostIdentApplication.class, args);

    }


}
