package no.visma.catfacts.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static no.visma.catfacts.domain.Catfact.TABLE_NAME;

@Data
@Entity
@Builder
@Table(name = TABLE_NAME)
public class Catfact {
    static final String TABLE_NAME = "T_CATFACTS";
    static final String SEQUENCE_NAME = TABLE_NAME + "_SEQ";

    @Id
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, initialValue = 10, allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @Column(name = "ID", nullable = false, updatable = false)
    private long id;

    @Column(name = "FACT", nullable = false, updatable = false)
    private String fact;

    @Column(name = "LENGTH", updatable = false)
    private int length;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    private LocalDateTime createdDate;
}

