package no.visma.catfacts.consumer;

import no.visma.catfacts.exceptions.CatfactsFunctionException;
import no.visma.catfacts.exceptions.CatfactsTechnicalException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

import static java.lang.String.format;

@Component
public class CatFactConsumer {
    private final RestTemplate restTemplate;
    private final CatFactProperties catFactProperties;

    @Inject
    public CatFactConsumer(RestTemplate restTemplate, CatFactProperties catFactProperties) {
        this.restTemplate = restTemplate;
        this.catFactProperties = catFactProperties;
    }

    public CatFactResponse getRandomCatFact() {
        try {
            return restTemplate.getForEntity(catFactProperties.getUrl(), CatFactResponse.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new CatfactsFunctionException(format("Functional error calling %s. HttpStatusCode=%s, ErrorMessage=%s ", catFactProperties.getUrl(), e.getStatusCode(), e.getMessage()));
        } catch (HttpServerErrorException e) {
            throw new CatfactsTechnicalException(format("Technical error calling %s. HttpStatusCode=%s, ErrorMessage=%s ", catFactProperties.getUrl(), e.getStatusCode(), e.getMessage()));
        }
    }
}
