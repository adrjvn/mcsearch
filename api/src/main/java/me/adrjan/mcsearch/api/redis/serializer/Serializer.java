package me.adrjan.mcsearch.api.redis.serializer;

public interface Serializer {

    <T> String serialize(T object);

    <T> T deserialize(String json, Class<T> clazz);
}
