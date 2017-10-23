package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Hotel;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Quarto;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.repository.HotelRepository;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.repository.QuartoRepository;
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
 * @date 22/10/2017, 17:34:15
 */
@RequestScoped
public class HotelQuartoService {

    @Inject
    private HotelRepository hotelRepository;
    @Inject
    private QuartoRepository quartoRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@DefaultValue("-1") @PathParam("hotelId") int hotelId) {
        Hotel hotel = hotelRepository.buscarHotel(hotelId);
        List<Quarto> quartos = hotel.getQuartos();
        
        GenericEntity<List<Quarto>> entity
                = new GenericEntity<List<Quarto>>(quartos) {
        };
        
        return Response.ok(entity).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarQuarto(
            @DefaultValue("-1") @PathParam("hotelId") int hotelId,
            Quarto quarto, @Context UriInfo uriInfo) {

        Hotel hotel = hotelRepository.buscarHotel(hotelId);
        quartoRepository.salvarQuarto(quarto);
        hotel.addQuarto(quarto);
        hotelRepository.atualizarHotel(hotel);

        URI uri = QuartoService.getUri(quarto.getId(), uriInfo);
        return Response.created(uri).build();
    }

    @DELETE
    @Path("{quartoId}")
    public Response deletarQuarto(
            @DefaultValue("-1") @PathParam("hotelId") int hotelId,
            @DefaultValue("-1") @PathParam("quartoId") int quartoId) {

        Hotel hotel = hotelRepository.buscarHotel(hotelId);
        hotel.removerQuarto(quartoId);
        hotelRepository.atualizarHotel(hotel);

        return Response.ok().build();
    }
    
    @GET
    @Path("{quartoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuarto(
            @DefaultValue("-1") @PathParam("quartoId") int quartoId) {
        Quarto q = quartoRepository.buscarQuarto(quartoId);
        return Response.ok(q).build();
    }

}
