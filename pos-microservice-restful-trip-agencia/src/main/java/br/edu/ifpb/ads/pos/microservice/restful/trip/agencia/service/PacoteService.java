package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Contrato;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Pacote;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.resource.ContratoResource;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.skinny.PacoteSkinny;
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
 * @date 23/10/2017, 08:24:17
 */
@Path("pacotes")
@RequestScoped
public class PacoteService {

    @Inject
    private PacoteRepository pacoteRepository;

    public static URI getUri(int pacoteId, UriInfo uriInfo) {

        return uriInfo.getBaseUriBuilder()
                .path(PacoteService.class)
                .path(String.valueOf(pacoteId))
                .build();
    }

    @GET
    @Path("{pacoteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarById(
            @DefaultValue("-1") @PathParam("pacoteId") int pacoteId,
            @Context UriInfo uriInfo) throws Exception {
        Pacote pacote = this.pacoteRepository.buscarPacote(pacoteId);
        PacoteSkinny pacoteSkinny = PacoteSkinny.valueOf(pacote, uriInfo);
        return Response.ok(pacoteSkinny).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@Context UriInfo uriInfo) throws Exception {

        List<PacoteSkinny> pacoteSkinnys = new ArrayList<>();
        List<Pacote> pacotes = this.pacoteRepository.listarPacotes();

        for (Pacote pacote : pacotes) {
            pacoteSkinnys.add(PacoteSkinny.valueOf(pacote, uriInfo));
        }

        GenericEntity<List<PacoteSkinny>> entity = new GenericEntity<List<PacoteSkinny>>(pacoteSkinnys) {
        };

        return Response.ok(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Pacote pacote) throws Exception {
        pacoteRepository.atualizarPacote(pacote);
        return Response.ok().build();
    }
}
