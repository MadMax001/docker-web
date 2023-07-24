package ru.opfr.dockersimple;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.opfr.dockersimple.model.Visit;
import ru.opfr.dockersimple.repository.VisitRepository;
import ru.opfr.dockersimple.service.LogService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationStartInformer {
    private final VisitRepository visitRepository;
    private final LogService logService;

    @EventListener
    public void logAllVisits(ContextRefreshedEvent event) {
        List<Visit> allVisits = visitRepository.findAll();
        allVisits.forEach(visit -> logService.info(visit.toString()));
    }


}
