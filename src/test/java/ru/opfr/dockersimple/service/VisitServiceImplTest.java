package ru.opfr.dockersimple.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import ru.opfr.dockersimple.model.Visit;
import ru.opfr.dockersimple.repository.VisitRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VisitServiceImplTest {
    @Mock
    private VisitRepository visitRepository;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private VisitServiceImpl visitService;

    @Test
    void newVisit_andCheckResponse() {
        var created = LocalDateTime.now();
        when(visitRepository.save(any(Visit.class))).then(invocation -> {
            Visit visit = invocation.getArgument(0);
            visit.setCreated(created);
            visit.setId(100L);
            return visit;
        });

        var ipAddress = "10.10.10.10";
        when(clientService.getIPAddress(any())).thenReturn(ipAddress);

        Visit visit = visitService.newHttpVisit(new MockHttpServletRequest());
        verify(visitRepository, times(1)).save(any(Visit.class));
        verify(clientService, times(1)).getIPAddress(any(HttpServletRequest.class));
        assertEquals(100L, visit.getId());
        assertEquals(created, visit.getCreated());
        assertEquals(ipAddress, visit.getIps());

    }
}
