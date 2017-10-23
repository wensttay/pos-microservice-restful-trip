package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Agencia;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.resource.AgenciaResource;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.resource.AgenciaResourceConverter;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.skinny.AgenciaSkinny;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.repository.AgenciaRepository;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 08:24:44
 */
@Path("agencias")
@RequestScoped
public class AgenciaService {
    
    @Inject
    private AgenciaRepository agenciaRepository;
    @Inject
    private AgenciaContratoService agenciaContratoService;
    @Inject
    private AgenciaPacoteService agenciaPacoteService;
    @Inject
    private AgenciaResourceConverter converter;
    
    public static URI getUri(int agenciaId, UriInfo uriInfo) {

        return uriInfo.getBaseUriBuilder()
                .path(AgenciaService.class)
                .path(String.valueOf(agenciaId))
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvar(AgenciaResource agencia, @Context UriInfo uriInfo) throws Exception {
        Agencia convert = converter.convert(agencia);
        this.agenciaRepository.salvarAgencia(convert);
        URI createdUri = getUri(convert.getId(), uriInfo);
        
        return Response.created(createdUri).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(AgenciaResource agencia) throws Exception {
        Agencia a = converter.convert(agencia);
        a.setId(agencia.getId());
        a.setContratos(agenciaRepository.buscarAgenciaContratos(agencia.getId()));
        a.setPacotes(agenciaRepository.buscarAgenciaPacotes(agencia.getId()));
        
        this.agenciaRepository.atualizarAgencia(a);
        return Response.ok().build();
    }

    @DELETE
    @Path("{agenciaId}")
    public Response deletar(@DefaultValue("-1") @PathParam("agenciaId") int agenciaId) {
        this.agenciaRepository.deletarAgencia(agenciaId);
        return Response.ok().build();
    }

    @GET
    @Path("{agenciaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarById(@DefaultValue("-1") @PathParam("agenciaId") int agenciaId) {
        Agencia agencia = this.agenciaRepository.buscarAgencia(agenciaId);
        return Response.ok(agencia).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@Context UriInfo uriInfo) {

        List<AgenciaSkinny> agenciaSkinnys = new ArrayList<>();
        List<Agencia> agencias = agenciaRepository.listarAgencias();
        
        for (Agencia a : agencias) {
            agenciaSkinnys.add(AgenciaSkinny.valueOf(a, uriInfo));
        }

        GenericEntity<List<AgenciaSkinny>> entity
                = new GenericEntity<List<AgenciaSkinny>>(agenciaSkinnys) {
        };

        return Response.ok(entity).build();
    }

    @Path("{agenciaId}/contratos")
    public AgenciaContratoService agenciaContratos() {
        return agenciaContratoService;
    }
    
    @Path("{agenciaId}/pacotes")
    public AgenciaPacoteService agenciaPacotes() {
        return agenciaPacoteService;
    }
}
