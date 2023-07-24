package ru.opfr.dockersimple.service;

import ru.opfr.dockersimple.model.Visit;

import javax.servlet.http.HttpServletRequest;

public interface VisitService {
    Visit newHttpVisit(HttpServletRequest request);
}
