package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.mapper;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 17/10/2017, 11:10:16
 */
public class MapperProducer {

    @RequestScoped
    @Default
    @Produces
    public Mapper getMapper() {
        return new Mapper();
    }
}
