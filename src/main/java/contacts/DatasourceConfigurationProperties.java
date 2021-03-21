package contacts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource")
@Getter @Setter
public class DatasourceConfigurationProperties {
    private String url;
    private String driverClassName;
    private String username;
}
