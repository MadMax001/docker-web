package ru.opfr.dockersimple.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Test
    void getIPAddressFromRequest() {
        ClientService clientService = new ClientServiceImpl();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("2.2.2.2");

        String clientIP = clientService.getIPAddress(request);
        assertEquals("2.2.2.2", clientIP);

    }

    @Test
    void getIPAddressFromHeader() {
        ClientService clientService = new ClientServiceImpl();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr(null);
        request.addHeader("HTTP_CLIENT_IP", "3.3.3.3");

        String clientIP = clientService.getIPAddress(request);
        assertEquals("3.3.3.3", clientIP);

    }

    @Test
    void getSameIPAddresses_FromHeader_AndFromRequest() {
        ClientService clientService = new ClientServiceImpl();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("4.4.4.4");
        request.addHeader("HTTP_CLIENT_IP", "4.4.4.4");

        String clientIP = clientService.getIPAddress(request);
        assertEquals("4.4.4.4", clientIP);

    }

    @Test
    void getDifferentIPAddresses_FromHeader_AndFromRequest() {
        ClientService clientService = new ClientServiceImpl();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("4.4.4.4");
        request.setRemoteAddr("1.1.1.1");
        request.addHeader("HTTP_CLIENT_IP", "4.4.4.4");

        String clientIP = clientService.getIPAddress(request);
        assertEquals("1.1.1.1; 4.4.4.4", clientIP);

    }

}
