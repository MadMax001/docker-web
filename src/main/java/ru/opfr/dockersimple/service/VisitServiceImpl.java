package ru.opfr.dockersimple.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.opfr.dockersimple.model.Visit;
import ru.opfr.dockersimple.repository.VisitRepository;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final ClientService clientService;

    @Override
    public Visit newHttpVisit(HttpServletRequest request) {
        Visit visit = new Visit();
        visit.setIps(clientService.getIPAddress(request));
        visitRepository.save(visit);
        return visit;
    }
}
