package no.visma.catfacts.mapper;

import no.visma.catfacts.domain.Catfact;
import org.springframework.stereotype.Component;

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

                    .build());
        }
        return catfacts;
    }
}
