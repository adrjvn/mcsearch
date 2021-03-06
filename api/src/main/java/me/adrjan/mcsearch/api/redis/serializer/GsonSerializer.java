package me.adrjan.mcsearch.api.redis.serializer;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GsonSerializer implements Serializer {

    private final Gson gson;

    @Override
    public <T> String serialize(T object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T deserialize(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}