package ru.opfr.dockersimple.service;

import javax.servlet.http.HttpServletRequest;

public interface ClientService {
    String getIPAddress(HttpServletRequest request);
}
