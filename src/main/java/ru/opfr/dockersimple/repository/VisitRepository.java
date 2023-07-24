package ru.opfr.dockersimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.opfr.dockersimple.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
