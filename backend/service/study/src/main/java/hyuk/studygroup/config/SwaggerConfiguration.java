package hyuk.studygroup.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("스터디 모집 CRUD Service API")
                        .version("v1")
                        .description("스터디 모집 API"));
    }
}
