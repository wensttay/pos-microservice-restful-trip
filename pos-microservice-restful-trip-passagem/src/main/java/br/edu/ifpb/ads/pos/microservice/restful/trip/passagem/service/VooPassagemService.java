package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Passagem;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Voo;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.repository.PassagemRepository;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.repository.VooRepository;
import java.net.URI;
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
 * @date 23/10/2017, 02:35:44
 */
@RequestScoped
public class VooPassagemService {

    @Inject
    private VooRepository vooRepository;
    @Inject
    private PassagemRepository passagemRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@DefaultValue("-1") @PathParam("vooId") int vooId) {
        Voo voo = vooRepository.buscarVoo(vooId);
        List<Passagem> passagens = voo.getPassagems();

        GenericEntity<List<Passagem>> entity
                = new GenericEntity<List<Passagem>>(passagens) {
        };

        return Response.ok(entity).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarPassagem(
            @DefaultValue("-1") @PathParam("vooId") int vooId,
            Passagem passagem, @Context UriInfo uriInfo) {

        Voo voo = vooRepository.buscarVoo(vooId);
        passagemRepository.salvarPassagem(passagem);
        voo.addPassagem(passagem);
        vooRepository.atualizarVoo(voo);

        URI uri = PassagemService.getUri(passagem.getId(), uriInfo);
        return Response.created(uri).build();
    }

    @DELETE
    @Path("{passagemId}")
    public Response deletarPassagem(
            @DefaultValue("-1") @PathParam("vooId") int vooId,
            @DefaultValue("-1") @PathParam("passagemId") int passagemId) {

        Voo voo = vooRepository.buscarVoo(vooId);
        voo.removerPassagem(passagemId);
        vooRepository.atualizarVoo(voo);

        return Response.ok().build();
    }

    @GET
    @Path("{passagemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPassagem(
            @DefaultValue("-1") @PathParam("passagemId") int passagemId) {
        Passagem passagem = passagemRepository.buscarPassagem(passagemId);
        return Response.ok(passagem).build();
    }
}
