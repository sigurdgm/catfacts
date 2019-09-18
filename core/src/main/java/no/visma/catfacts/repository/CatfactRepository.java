package no.visma.catfacts.repository;

import no.visma.catfacts.domain.Catfact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatfactRepository extends CrudRepository<Catfact, Long> {
    List<Catfact> findAllByLength(Integer length);
}
