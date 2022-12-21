package org.bootcamp4.api0009.libraries;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpFunction {
    private HttpFunction() {}

    public static Map<String, String> getHeaders(HttpServletRequest servletRequest) {
        Map<String, String> headers = new HashMap<>();
        final Enumeration<String> a = servletRequest.getHeaderNames();
        while (a.hasMoreElements()) {
            String value = a.nextElement();
            headers.put(value, servletRequest.getHeader(value));
        }
        return headers;
    }

    public static Map<String, Object> getRequestInfo(HttpServletRequest servletRequest) {
        Map<String, Object> info = new HashMap<>();

        info.put("method", servletRequest.getMethod());
        info.put("path", servletRequest.getServletPath());
        info.put("cookies", servletRequest.getCookies());
        info.put("headers", getHeaders(servletRequest));
        info.put("contentType", servletRequest.getContentType());
        info.put("protocol", servletRequest.getProtocol());

        info.put("serverName", servletRequest.getServerName());
        info.put("serverPort", servletRequest.getServerPort());

        info.put("localAddr", servletRequest.getLocalAddr());
        info.put("localPort", servletRequest.getLocalPort());

        info.put("remoteHost", servletRequest.getRemoteHost());
        info.put("remotePort", servletRequest.getRemotePort());
        info.put("remoteUser", servletRequest.getRemoteUser());

        info.put("authType", servletRequest.getAuthType());
        info.put("isSecure", servletRequest.isSecure() ? "Yes" : "No");

        return info;
    }
}
