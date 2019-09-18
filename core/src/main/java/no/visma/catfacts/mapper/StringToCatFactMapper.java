package no.visma.catfacts.mapper;

import no.visma.catfacts.domain.Catfact;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class StringToCatFactMapper {
    public List<Catfact> toCatFact(List<String> facts) {
        if (facts == null) {
            throw new RuntimeException();
        }
        List<Catfact> catfacts = new ArrayList<>();
        for (String fact : facts) {
            catfacts.add(Catfact.builder()
                    .fact(fact)
                    .createdDate(LocalDateTime.now())
                    .length(fact.length())
                    .build());
        }
        return catfacts;
    }
}
