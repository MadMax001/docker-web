package ru.opfr.dockersimple.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "visit", schema = "docker_test")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_sequence")
    @SequenceGenerator(name="visit_sequence", sequenceName = "docker_test.visit_sequence", allocationSize = 1)
    private Long id;
    private String ips;
    private LocalDateTime created;

    @PrePersist
    private void onCreate() {
        created = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visit)) return false;
        Visit visit = (Visit) o;
        return Objects.equals(id, visit.id) && Objects.equals(ips, visit.ips) &&
                Duration.between(created, ((Visit) o).created).toMillis() == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ips, created);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LL.yyyy HH:mm:ss");
        return "Visit{" +
                ", ip='" + ips + '\'' +
                ", created=" + Optional.ofNullable(created).map(crt -> crt.format(formatter)).orElse("") +
                '}';
    }
}
