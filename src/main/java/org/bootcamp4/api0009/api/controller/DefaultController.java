package org.bootcamp4.api0009.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import org.bootcamp4.api0009.libraries.specifical.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "*", "/**" })
public class DefaultController {

    @RequestMapping()
    public ResponseEntity<HashMap<String, Object>> def(HttpServletRequest request) {
        return Response.get(request, response -> {
            response.setErrorMsg("notfound");
            response.setErrorCode(101);
            response.setStatusCode(HttpStatus.NOT_FOUND);
            return null;
        }, getClass(), "def");
    }
}
