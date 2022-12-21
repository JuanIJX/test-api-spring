package org.bootcamp4.api0009.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import org.bootcamp4.api0009.libraries.specifical.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testpath")
public class TestController {

    @RequestMapping()
    public ResponseEntity<HashMap<String, Object>> def(HttpServletRequest request) {
        return Response.get(request, response -> "default "+getClass().getSimpleName(), getClass(), "def");
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> get(@PathVariable int id, HttpServletRequest request) {
        return Response.get(request, response -> "id obtenida: " + id, getClass(), "get");
    }
}
