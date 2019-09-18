package no.visma.catfacts.mapper;

import no.visma.catfacts.domain.Catfact;
import org.junit.Test;

import java.util.List;

import static java.time.LocalDateTime.now;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;


public class StringToCatFactMapperTest {

    private StringToCatFactMapper stringToCatFactMapper = new StringToCatFactMapper();

    @Test
    public void mapsFact() {
        final String fact = "TESTFACT";
        List<String> facts = List.of(fact);

        List<Catfact> catfacts = stringToCatFactMapper.toCatFact(facts);

        assertThat(catfacts.size(), is(1));
        assertThat(catfacts.get(0).getFact(), is(fact));
        assertThat(catfacts.get(0).getCreatedDate(), is(both(greaterThan(now().minusSeconds(1))).and(lessThan(now().plusSeconds(1)))));
    }

    @Test(expected = RuntimeException.class)
    public void throwsExceptionWhenFactIsNull() {
        stringToCatFactMapper.toCatFact(null);
    }
}