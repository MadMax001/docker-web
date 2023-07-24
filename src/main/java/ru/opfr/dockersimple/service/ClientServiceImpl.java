package ru.opfr.dockersimple.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ClientServiceImpl implements ClientService {
    private static final List<String> POSSIBLE_IP_HEADERS = List.of(
            "X-Forwarded-For",
            "HTTP_FORWARDED",
            "HTTP_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_CLIENT_IP",
            "HTTP_VIA",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "REMOTE_ADDR"
    );
    @Override
    public String getIPAddress(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        var addressFromRequest = request.getRemoteAddr();
        if (Objects.nonNull(addressFromRequest) && !addressFromRequest.isBlank())
            sb.append(addressFromRequest);

        var addressFromHeader = getIpAddressFromHeader(request);
        if (Objects.nonNull(addressFromHeader) && !addressFromHeader.isBlank()
                && !addressFromHeader.equals(addressFromRequest)) {
            if (sb.length() > 0)
                sb.append("; ");
            sb.append(addressFromHeader);
        }

        return sb.toString();
    }

    private String getIpAddressFromHeader(HttpServletRequest request) {
        for (String ipHeader : POSSIBLE_IP_HEADERS) {
            String headerValue = Collections.list(request.getHeaders(ipHeader)).stream()
                    .filter(StringUtils::hasLength)
                    .findFirst()
                    .orElse(null);

            if (headerValue != null) {
                return headerValue;
            }
        }

        return null;
    }
}
