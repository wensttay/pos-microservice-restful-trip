package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Reserva;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.resource.ReservaResource;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.resource.ReservaResourceConverter;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.skinny.ReservaSkinny;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.repository.ReservaRepository;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.service.consumer.ClienteConsumer;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
 * @date 22/10/2017, 17:34:24
 */
@Path("reservas")
@RequestScoped
public class ReservaService {

    @Inject
    private ReservaRepository reservaRepository;
    @Inject
    private ReservaResourceConverter reservaResourceConverter;

    public static URI getUri(int reservaId, UriInfo uriInfo) {

        return uriInfo.getBaseUriBuilder()
                .path(ReservaService.class)
                .path(String.valueOf(reservaId))
                .build();
    }

    @GET
    @Path("{reservaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarById(
            @DefaultValue("-1") @PathParam("reservaId") int reservaId,
            @Context UriInfo uriInfo) {
        Reserva reserva = this.reservaRepository.buscarReserva(reservaId);
        ReservaSkinny reservaSkinny = ReservaSkinny.valueOf(reserva, uriInfo);
        return Response.ok(reservaSkinny).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@Context UriInfo uriInfo) {

        List<ReservaSkinny> reservaSkinnys = new ArrayList<>();
        List<Reserva> reservas = this.reservaRepository.listarReservas();

        for (Reserva reserva : reservas) {
            reservaSkinnys.add(ReservaSkinny.valueOf(reserva, uriInfo));
        }

        GenericEntity<List<ReservaSkinny>> entity = new GenericEntity<List<ReservaSkinny>>(reservaSkinnys) {
        };

        return Response.ok(entity).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionar(ReservaResource reservaResource, @Context UriInfo uriInfo) throws Exception {

        Reserva reserva = reservaResourceConverter.convert(reservaResource);
        reservaRepository.salvarReserva(reserva);
        URI uri = getUri(reserva.getId(), uriInfo);

        return Response.created(uri).build();
    }

    @DELETE
    @Path("{reservaId}")
    public Response deletar(
            @DefaultValue("-1") @PathParam("reservaId") int reservaId) {
        reservaRepository.deletarReserva(reservaId);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(ReservaResource reservaResource) throws Exception {

        Reserva reserva = reservaResourceConverter.convert(reservaResource);
        reserva.setId(reservaResource.getId());
        reservaRepository.atualizarReserva(reserva);

        return Response.ok().build();
    }
}
