package ru.opfr.dockersimple;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.opfr.dockersimple.model.builders.VisitTestBuilder;
import ru.opfr.dockersimple.repository.VisitRepository;
import ru.opfr.dockersimple.service.LogService;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ApplicationStartInformerTest {
    @Mock
    VisitRepository visitRepository;

    @Mock
    LogService logService;

    @InjectMocks
    ApplicationStartInformer informer;

    @Test
    void checkHowManyTimesInvokesLogger() {
        when(visitRepository.findAll()).thenReturn(
                IntStream.range(0, 10).boxed().map(
                        i -> VisitTestBuilder.aVisit().withIps("10.10.10." + i).build()
                ).collect(Collectors.toList())
        );
        informer.logAllVisits(null);

        verify(logService, times(10)).info(any(String.class));
    }
}