package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Quarto;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.skinny.HotelSkinny;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.repository.QuartoRepository;
import java.net.URI;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
 * @date 22/10/2017, 21:22:09
 */

@Path("quartos")
@RequestScoped
public class QuartoService {
    
    @Inject
    private QuartoRepository quartoRepository;
    
    public static URI getUri(int quartoId, UriInfo uriInfo) {

        return uriInfo.getBaseUriBuilder()
                .path(QuartoService.class)
                .path(String.valueOf(quartoId))
                .build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        List<Quarto> quartos = quartoRepository.listarQuartos();
        GenericEntity<List<Quarto>> entity
                = new GenericEntity<List<Quarto>>(quartos) {
        };
        return Response.ok(entity).build();
    }
    
    @GET
    @Path("{quartoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarById(
            @DefaultValue("-1") @PathParam("quartoId") int quartoId) {
        Quarto quarto = quartoRepository.buscarQuarto(quartoId);
        return Response.ok(quarto).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Quarto quarto) {
        quartoRepository.atualizarQuarto(quarto);
        return Response.ok().build();
    }
    
}
