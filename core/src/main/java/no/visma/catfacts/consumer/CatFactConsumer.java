package no.visma.catfacts.consumer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import no.visma.catfacts.exceptions.CatfactsFunctionException;
import no.visma.catfacts.exceptions.CatfactsTechnicalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

import static java.lang.String.format;

@Component
//@RequiredArgsConstructor
public class CatFactConsumer {
    /*
     * Alternativt:
     * -----------
     *
     * @Autowired
     * private RestTemplate restTemplate;
     *
     * @Value("${catfact.ninja.url}")
     * private String url;
     *
     * -----------
     */

    private final RestTemplate restTemplate;
    private final String url;

    @Inject
    public CatFactConsumer(RestTemplate restTemplate, @Value("${catfact.ninja.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public CatFactResponse getRandomCatFact() {
        try {
            return restTemplate.getForEntity(url, CatFactResponse.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new CatfactsFunctionException(format("Functional error calling %s. HttpStatusCode=%s, ErrorMessage=%s ", url, e.getStatusCode(), e.getMessage()));
        } catch (HttpServerErrorException e) {
            throw new CatfactsTechnicalException(format("Technical error calling %s. HttpStatusCode=%s, ErrorMessage=%s ", url, e.getStatusCode(), e.getMessage()));
        }
    }
}
