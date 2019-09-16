package no.visma.catfacts.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ConsumerConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .setReadTimeout(Duration.ofSeconds(3)) // Mulighet til å sette opp flere konfigurasjoner
                .build();
    }

//    @Bean
//    CatFactConsumer catFactConsumer(RestTemplate restTemplate, @Value("${catfact.ninja.url}") String url) {
//        return new CatFactConsumer(restTemplate, url);
//    }
}
