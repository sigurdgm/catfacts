package no.visma.catfacts.service;

import no.visma.catfacts.domain.CatFact;
import no.visma.catfacts.mapper.StringToCatFactMapper;
import no.visma.catfacts.repository.CatfactRepository;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FactSaverServiceTest {
    @Mock
    private CatfactRepository catfactRepositoryMock;

    @Mock
    private StringToCatFactMapper stringToCatFactMapper;

    @InjectMocks
    private FactSaverService factSaverService;

    @Captor
    private ArgumentCaptor<List<CatFact>> catFactsCaptor;

    @Test
    public void savesAllFacts() {
        List<String> fact = List.of("Fact");
        CatFact catFact = CatFact.builder().fact("Fact").length(4).createdDate(now()).build();
        when(stringToCatFactMapper.toCatFact(fact))
                .thenReturn(List.of(catFact));

        factSaverService.saveAllAsCatfact(fact);

        verify(stringToCatFactMapper).toCatFact(fact);
        verify(catfactRepositoryMock).saveAll(catFactsCaptor.capture());
        List<CatFact> catFacts = catFactsCaptor.getValue();
        assertThat(catFacts.size(), is(1));
        assertThat(catFacts.get(0), is(catFact));
    }
}