package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.resource;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Agencia;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 07:53:10
 */
@ApplicationScoped
public class AgenciaResourceConverter {
    
    public Agencia convert(AgenciaResource agenciaResource) throws Exception {
        
        return new Agencia(agenciaResource.getNome(), 
                agenciaResource.getEndereco(), 
                agenciaResource.getCNPJ());
        
    }
}
