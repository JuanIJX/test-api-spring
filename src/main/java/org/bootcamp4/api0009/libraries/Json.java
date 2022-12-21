package org.bootcamp4.api0009.libraries;

import com.google.gson.Gson;
import java.util.Map;

public class Json {
    private Json() {}

    public static Map<String, Object> parseToMap(String body) {
        Gson gson = new Gson();
        return gson.fromJson(body, Map.class);
    }
}
