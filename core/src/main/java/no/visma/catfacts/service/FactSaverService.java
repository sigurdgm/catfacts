package no.visma.catfacts.service;

import lombok.RequiredArgsConstructor;
import no.visma.catfacts.domain.CatFact;
import no.visma.catfacts.mapper.StringToCatFactMapper;
import no.visma.catfacts.repository.CatfactRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FactSaverService {
    private final CatfactRepository catfactRepository;
    private final StringToCatFactMapper stringToCatFactMapper;

    public void saveAllAsCatfact(List<String> facts) {
        List<CatFact> catFacts = stringToCatFactMapper.toCatFact(facts);
        catfactRepository.saveAll(catFacts);
    }

}
