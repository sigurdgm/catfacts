package no.visma.catfacts;

import lombok.extern.slf4j.Slf4j;
import no.visma.catfacts.consumer.CatFactConsumer;
import no.visma.catfacts.domain.Catfact;
import no.visma.catfacts.exceptions.CatfactsFunctionException;
import no.visma.catfacts.repository.CatfactRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RestController
@RequestMapping("/catfacts")
@Slf4j
public class Controller {

    private final CatFactConsumer catFactConsumer;
    private final CatfactRepository catfactRepository;

    public Controller(CatFactConsumer catFactConsumer, CatfactRepository catfactRepository) {
        this.catFactConsumer = catFactConsumer;
        this.catfactRepository = catfactRepository;
    }

    @GetMapping
    @ResponseBody
    public String getRandomCatFact() {
        log.info("Generating random catfact");
        return catFactConsumer.getRandomCatFact().getFact();
    }

    @GetMapping("/{id}")
    public Catfact getCatfactById(@PathVariable("id") Long id) {
        log.info(format("Looking up catfact with id=%s", id));
        return catfactRepository.findById(id).orElseThrow(() -> new CatfactsFunctionException(format("Could not find a catfact with id=%s", id)));
    }

    @PostMapping("/populate")
    public void populateCatfactsDb(@RequestBody List<String> facts) {
        log.info("Populating db with catfacts");
        List<Catfact> catfacts = facts.stream()
                .map(fact -> Catfact.builder().fact(fact).createdDate(LocalDateTime.now()).length(fact.length()).build())
                .collect(Collectors.toList());
        catfactRepository.saveAll(catfacts);
    }
}
