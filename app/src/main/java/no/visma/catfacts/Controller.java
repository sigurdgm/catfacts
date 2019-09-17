package no.visma.catfacts;

import lombok.extern.slf4j.Slf4j;
import no.visma.catfacts.consumer.CatFactConsumer;
import no.visma.catfacts.domain.Catfact;
import no.visma.catfacts.exceptions.CatfactsFunctionException;
import no.visma.catfacts.mapper.StringToCatFactMapper;
import no.visma.catfacts.repository.CatfactRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/catfacts")
@Slf4j
public class Controller {

    private final CatFactConsumer catFactConsumer;
    private final CatfactRepository catfactRepository;
    private final StringToCatFactMapper stringToCatFactMapper;

    public Controller(CatFactConsumer catFactConsumer, CatfactRepository catfactRepository, StringToCatFactMapper stringToCatFactMapper) {
        this.catFactConsumer = catFactConsumer;
        this.catfactRepository = catfactRepository;
        this.stringToCatFactMapper = stringToCatFactMapper;
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
        List<Catfact> catfacts = stringToCatFactMapper.toCatFact(facts);
        catfactRepository.saveAll(catfacts);
    }
}
