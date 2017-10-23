package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 17/10/2017, 11:10:16
 */
@ApplicationScoped
public class Mapper {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public String toString(Object obj) throws MapperException {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            throw new MapperException(ex);
        }
    }

    public <T> T toObject(String json, Class<T> clazz) throws MapperException {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException ex) {
            throw new MapperException(ex);
        }
    }

    public <T> List<T> toList(String json) throws MapperException {
        try {
            return objectMapper.readValue(json, new TypeReference<List<T>>() {
            });
        } catch (IOException ex) {
            throw new MapperException(ex);
        }
    }

    public <Z> List<Z> toList(String json, Class<Z> clazz) throws MapperException {
        try {
            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, clazz);
            return objectMapper.readValue(json, javaType);
        } catch (IOException ex) {
            throw new MapperException(ex);
        }
    }
}
