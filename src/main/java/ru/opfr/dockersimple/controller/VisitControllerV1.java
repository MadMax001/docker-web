package ru.opfr.dockersimple.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.opfr.dockersimple.model.ResponseWrapper;
import ru.opfr.dockersimple.model.Visit;
import ru.opfr.dockersimple.service.VisitService;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VisitControllerV1 {
    private final VisitService visitService;

    @GetMapping("/visit")
    public ResponseEntity<ResponseWrapper> visit(HttpServletRequest request) {
        Visit visit = visitService.newHttpVisit(request);
        ResponseWrapper wrapper = ResponseWrapper.builder().version("v1").ip(visit.getIps()).build();
        return new ResponseEntity<>(wrapper, CREATED);

    }

}
