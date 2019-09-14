package no.visma.catfacts.consumer;

import lombok.Value;

@Value
public class CatFactResponse {
    private final String fact;
    private final String length;
}
