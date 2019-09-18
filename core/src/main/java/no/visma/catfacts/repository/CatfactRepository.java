package no.visma.catfacts.repository;

import no.visma.catfacts.domain.CatFact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatfactRepository extends CrudRepository<CatFact, Long> {
    List<CatFact> findAllByLength(Integer length);
}
