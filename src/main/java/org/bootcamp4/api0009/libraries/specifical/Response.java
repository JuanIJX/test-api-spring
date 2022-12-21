package org.bootcamp4.api0009.libraries.specifical;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/*
    - 0XX: ok
    - 1XX: Controller exception
        - 101: NotFound
        - 102: MethodArgumentTypeMismatchException
    - 2XX:
    - 3XX
    - 9XX: interno del servicio
*/

public class Response {
    private static final boolean ENABLE_LOGS = true;
    private static final String BODY_NAME_ERROR = "error";
    private static final String BODY_NAME_REQUEST = "request";
    private static final String BODY_NAME_TIMESTAMP = "timestamp";
    private static final String BODY_NAME_BODY= "body";

    public interface ServiceController {
        public Object run(Response response) throws Exception;
    }

    public static ResponseEntity<HashMap<String, Object>> get(HttpServletRequest request, ServiceController runnable) { return get(request, runnable, null, null); }
    public static ResponseEntity<HashMap<String, Object>> get(
            @NotNull HttpServletRequest request,
            @NotNull ServiceController runnable,
            Class<?> className,
            String functionName
    ) {
        Response resp = new Response(request);
        if(ENABLE_LOGS)
            System.out.println(
                    "=============================" + '\n' +
                    "Ruta: " + request.getServletPath() + '\n' +
                    "Metodo: " + request.getMethod() + '\n' +
                    ((className != null) ? "Class: " + className.getSimpleName() + '\n' : "") +
                    ((functionName!=null && !functionName.isEmpty()) ? "Function: " + functionName + '\n' : "") +
                    "-----------------------------" + '\n' +
                    ""
            );

        try {
            resp.setBody(runnable.run(resp));
        }
        catch (NotFoundException e) {
            resp.setStatusCode(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(resp.getResponse(), resp.getStatusCode());
    }

    private HttpStatus statusCode;
    private HashMap<String, Object> bodyResponse;

    public Response() {
        this.statusCode = HttpStatus.OK;
        this.bodyResponse = new HashMap<String, Object>();

        setDefaultBodyResponse();
    }

    public Response(HttpServletRequest request) {
        this();
        setRequest(request);
    }

    private void setDefaultBodyResponse() {
        this.bodyResponse.put(BODY_NAME_ERROR, new HashMap<String, Object>());
        this.bodyResponse.put(BODY_NAME_REQUEST, new HashMap<String, Object>());
        this.bodyResponse.put(BODY_NAME_BODY, null);
        this.bodyResponse.put(BODY_NAME_TIMESTAMP, (new Date()).getTime());

        this.setErrorCode(0);
        this.setErrorMsg("ok");
    }

    private HashMap<String, Object> getMap(String key) {
        return ((HashMap<String, Object>) this.bodyResponse.get(key));
    }

    public void setStatusCode(HttpStatus code) {
        this.statusCode = code;
    }

    public void setErrorCode(int code) {
        getMap(BODY_NAME_ERROR).put("code", code);
    }

    public void setErrorMsg(String msg) {
        getMap(BODY_NAME_ERROR).put("msg", msg);
    }

    protected void setBody(Object body) {
        this.bodyResponse.put(BODY_NAME_BODY, body);
    }

    protected void setRequest(HttpServletRequest request) {
        getMap(BODY_NAME_REQUEST).put("ip", request.getRemoteAddr());
        getMap(BODY_NAME_REQUEST).put("method", request.getMethod());
        getMap(BODY_NAME_REQUEST).put("path", request.getServletPath());
    }

    public HashMap<String, Object> getResponse() {
        return this.bodyResponse;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }
}
