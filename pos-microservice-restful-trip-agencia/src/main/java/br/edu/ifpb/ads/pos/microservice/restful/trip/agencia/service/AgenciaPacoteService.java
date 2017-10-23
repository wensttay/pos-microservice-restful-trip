package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Agencia;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Pacote;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.skinny.PacoteSkinny;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.repository.AgenciaRepository;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.repository.PacoteRepository;
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
 * @date 23/10/2017, 08:59:18
 */
@RequestScoped
public class AgenciaPacoteService {

    @Inject
    private AgenciaRepository agenciaRepository;
    @Inject
    private PacoteRepository pacoteRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@DefaultValue("-1") @PathParam("agenciaId") int agenciaId, 
            @Context UriInfo uriInfo) throws Exception {
        Agencia agencia = agenciaRepository.buscarAgencia(agenciaId);
        
        List<Pacote> pacotes = agencia.getPacotes();
        List<PacoteSkinny> pacoteSkinnys = new ArrayList<>();
        
        for (Pacote pacote : pacotes) {
            pacoteSkinnys.add(PacoteSkinny.valueOf(pacote, uriInfo));
        }
        GenericEntity<List<PacoteSkinny>> entity
                = new GenericEntity<List<PacoteSkinny>>(pacoteSkinnys) {
        };

        return Response.ok(entity).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarPacote(
            @DefaultValue("-1") @PathParam("agenciaId") int agenciaId,
            Pacote pacote, @Context UriInfo uriInfo) {

        Agencia agencia = agenciaRepository.buscarAgencia(agenciaId);
        pacoteRepository.salvarPacote(pacote);
        agencia.addPacote(pacote);
        agenciaRepository.atualizarAgencia(agencia);

        URI uri = ContratoService.getUri(pacote.getId(), uriInfo);
        return Response.created(uri).build();
    }

    @DELETE
    @Path("{pacoteId}")
    public Response deletarContrato(
            @DefaultValue("-1") @PathParam("agenciaId") int agenciaId,
            @DefaultValue("-1") @PathParam("pacoteId") int pacoteId) {

        Agencia agencia = agenciaRepository.buscarAgencia(agenciaId);
        agencia.removePacote(pacoteId);
        agenciaRepository.atualizarAgencia(agencia);

        return Response.ok().build();
    }

    @GET
    @Path("{pacoteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContrato(
            @DefaultValue("-1") @PathParam("pacoteId") int pacoteId,
            @Context UriInfo uriInfo) throws Exception {
        
        Pacote pacote = pacoteRepository.buscarPacote(pacoteId);
        return Response.ok(PacoteSkinny.valueOf(pacote, uriInfo)).build();
    }
}
