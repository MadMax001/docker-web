package ru.opfr.dockersimple.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.opfr.dockersimple.model.Visit;
import ru.opfr.dockersimple.model.builders.VisitTestBuilder;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@ActiveProfiles("repo_test")
class VisitRepositoryIT {
    private final VisitRepository visitRepository;
    private final TestEntityManager entityManager;

    @Test
    void saveRepository_AndGetFromDB_AndCheck() {
        Visit visit = VisitTestBuilder.aVisit().build();
        visitRepository.save(visit);
        entityManager.flush();
        entityManager.clear();

        assertNotNull(visit.getId());
        Visit dbVisit = visitRepository.findById(visit.getId()).orElseThrow(() -> new RuntimeException("visit is not found"));
        assertNotNull(dbVisit);
        assertEquals(dbVisit, visit);

    }


}
