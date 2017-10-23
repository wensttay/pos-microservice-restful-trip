package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Hotel;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.skinny.HotelSkinny;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.repository.HotelRepository;
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
 * @date 22/10/2017, 17:33:59
 */
@Path("hoteis")
@RequestScoped
public class HotelService {

    @Inject
    private HotelRepository hotelRepository;
    @Inject
    private HotelQuartoService quartoService;

    public static URI getUri(int hotelId, UriInfo uriInfo) {

        return uriInfo.getBaseUriBuilder()
                .path(HotelService.class)
                .path(String.valueOf(hotelId))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvar(Hotel hotel, @Context UriInfo uriInfo) {
        this.hotelRepository.salvarHotel(hotel);
        URI createdUri = getUri(hotel.getId(), uriInfo);
        return Response.created(createdUri).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Hotel updatedHotel) {
        this.hotelRepository.atualizarHotel(updatedHotel);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{hotelId}")
    public Response deletar(@DefaultValue("-1") @PathParam("hotelId") int hotelId) {
        this.hotelRepository.deletarHotel(hotelId);
        return Response.ok().build();
    }

    @GET
    @Path("{hotelId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarById(@DefaultValue("-1") @PathParam("hotelId") int hotelId) {
        Hotel hotelFound = this.hotelRepository.buscarHotel(hotelId);
        return Response.ok(hotelFound).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@Context UriInfo uriInfo) {

        List<HotelSkinny> hotelSkinnys = new ArrayList<>();
        List<Hotel> hoteis = hotelRepository.listarHoteis();
        
        for (Hotel h : hoteis) {
            hotelSkinnys.add(HotelSkinny.valueOf(h, uriInfo));
        }

        GenericEntity<List<HotelSkinny>> entity
                = new GenericEntity<List<HotelSkinny>>(hotelSkinnys) {
        };

        return Response.ok(entity).build();
    }

    @Path("{hotelId}/quartos")
    public HotelQuartoService hotelQuartos() {
        return quartoService;
    }

}
