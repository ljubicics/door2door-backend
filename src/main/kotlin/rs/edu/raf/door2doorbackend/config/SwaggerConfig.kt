package rs.edu.raf.door2doorbackend.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    private final val APP_TITLE: String = "Door2Door API"
    private final val APP_DESCRIPTION: String = "Door2Door API Documentation"

    private final val APP_API_VERSION: String = "1.0.0"
    private final val APP_LICENSE: String = "LICENSE"
    private final val APP_LICENSE_URL: String = "LICENSE URL"

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title(APP_TITLE)
                    .description(APP_DESCRIPTION)
                    .version(APP_API_VERSION)
                    .license(License().name(APP_LICENSE).url(APP_LICENSE_URL))
            )
    }
}