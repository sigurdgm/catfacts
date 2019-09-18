package no.visma.catfacts;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.visma.catfacts.consumer.CatFactConsumer;
import no.visma.catfacts.domain.CatFact;
import no.visma.catfacts.exceptions.CatfactsFunctionException;
import no.visma.catfacts.repository.CatfactRepository;
import no.visma.catfacts.service.FactSaverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@Slf4j
@RestController
@RequestMapping("/catfacts")
@RequiredArgsConstructor
public class Controller {

    private final CatFactConsumer catFactConsumer;
    private final CatfactRepository catfactRepository;
    private final FactSaverService factSaverService;

    @GetMapping
    @ResponseBody
    public String getRandomCatFact() {
        log.info("Generating random catfact");
        return catFactConsumer.getRandomCatFact().getFact();
    }

    @GetMapping("/{id}")
    public CatFact getCatfactById(@PathVariable("id") Long id) {
        log.info(format("Looking up catfact with id=%s", id));
        return catfactRepository.findById(id).orElseThrow(() -> new CatfactsFunctionException(format("Could not find a catfact with id=%s", id)));
    }

    @PostMapping("/populate")
    public void populateCatfactsDb(@RequestBody List<String> facts) {
        log.info("Populating db with catfacts");
        factSaverService.saveAllAsCatfact(facts);
    }
}
