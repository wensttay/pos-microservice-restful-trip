package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Passagem;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.repository.PassagemRepository;
import java.net.URI;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 02:15:36
 */
@Path("passagens")
@RequestScoped
public class PassagemService {

    @Inject
    private PassagemRepository passagemRepository;
    
    public static URI getUri(int passagemId, UriInfo uriInfo) {

        return uriInfo.getBaseUriBuilder()
                .path(PassagemService.class)
                .path(String.valueOf(passagemId))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        List<Passagem> passagens = passagemRepository.listarPassagens();
        GenericEntity<List<Passagem>> entity
                = new GenericEntity<List<Passagem>>(passagens) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("{passagemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarById(
            @DefaultValue("-1") @PathParam("passagemId") int passagemId) {
        Passagem passagem = passagemRepository.buscarPassagem(passagemId);
        return Response.ok(passagem).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Passagem passagem) {
        passagemRepository.atualizarPassagem(passagem);
        return Response.ok().build();
    }

}
