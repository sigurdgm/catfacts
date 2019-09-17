package no.visma.catfacts.consumer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "catfact.ninja")
public class CatFactProperties {
    public String url;
}
