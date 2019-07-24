package project.post_ident.classes;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Path;
import java.nio.file.Paths;

@Configuration
public class Dummy implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("file:resources/","file:uploads/","file:C:\\Users\\krach\\Pictures")
                .setCachePeriod(0);
    }


}
