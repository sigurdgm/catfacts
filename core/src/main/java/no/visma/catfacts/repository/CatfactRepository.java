package no.visma.catfacts.repository;

import no.visma.catfacts.domain.Catfact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatfactRepository extends CrudRepository<Catfact, Long> {
    List<Catfact> findAllByLength(Integer length);
}
