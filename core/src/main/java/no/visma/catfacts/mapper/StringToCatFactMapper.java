package no.visma.catfacts.mapper;

import no.visma.catfacts.domain.CatFact;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class StringToCatFactMapper {
    public List<CatFact> toCatFact(List<String> facts) {
        if (facts == null) {
            throw new RuntimeException();
        }
        List<CatFact> catFacts = new ArrayList<>();
        for (String fact : facts) {
            catFacts.add(CatFact.builder()
                    .fact(fact)
                    .createdDate(LocalDateTime.now())
                    .length(fact.length())
                    .build());
        }
        return catFacts;
    }
}
