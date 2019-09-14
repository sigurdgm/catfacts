package no.visma.catfacts.repository;

import no.visma.catfacts.domain.Catfacts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatfactRepository extends CrudRepository<Catfacts, Long> {
    List<Catfacts> findAllByLength(Integer length);
}
