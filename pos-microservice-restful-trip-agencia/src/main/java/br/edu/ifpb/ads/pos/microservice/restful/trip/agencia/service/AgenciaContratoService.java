package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Agencia;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Contrato;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Pacote;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.resource.ContratoResource;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.resource.ContratoResourceConverter;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.skinny.ContratoSkinny;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.repository.AgenciaRepository;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.repository.ContratoRepository;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.HotelReservaConsumer;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.HotelReservaRequestEntity;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.PassagemReservaConsumer;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.PassagemReservaResquestEntity;
import java.net.URI;
import java.text.SimpleDateFormat;
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
 * @date 23/10/2017, 08:57:34
 */
@RequestScoped
public class AgenciaContratoService {

    @Inject
    private AgenciaRepository agenciaRepository;
    @Inject
    private ContratoRepository contratoRepository;

    @Inject
    private HotelReservaConsumer hotelReservaConsumer;
    @Inject
    private PassagemReservaConsumer passagemReservaConsumer;

    @Inject
    private ContratoResourceConverter converter;

    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@DefaultValue("-1") @PathParam("agenciaId") int agenciaId,
            @Context UriInfo uriInfo) throws Exception {
        Agencia agencia = agenciaRepository.buscarAgencia(agenciaId);
        List<Contrato> contratos = agencia.getContratos();
        List<ContratoSkinny> contratoSkinnys = new ArrayList<>();
        for (Contrato contrato : contratos) {
            contratoSkinnys.add(ContratoSkinny.valueOf(contrato, uriInfo));
        }
        GenericEntity<List<ContratoSkinny>> entity
                = new GenericEntity<List<ContratoSkinny>>(contratoSkinnys) {
        };

        return Response.ok(entity).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarContrato(
            @DefaultValue("-1") @PathParam("agenciaId") int agenciaId,
            ContratoResource contratoResource, @Context UriInfo uriInfo) throws Exception {

        Agencia agencia = agenciaRepository.buscarAgencia(agenciaId);
        Contrato contrato = fillContrato(contratoResource);
        
        System.out.println("CONTRATO <<<<<<<<<<<<<<<<<<<<< " + contrato);
        
        contratoRepository.salvarContrato(contrato);
        agencia.addContrato(contrato);
        agenciaRepository.atualizarAgencia(agencia);

        URI uri = ContratoService.getUri(contrato.getId(), uriInfo);
        return Response.created(uri).build();
    }

    @DELETE
    @Path("{contratoId}")
    public Response deletarContrato(
            @DefaultValue("-1") @PathParam("agenciaId") int agenciaId,
            @DefaultValue("-1") @PathParam("contratoId") int contratoId) {

        Contrato buscarContrato = contratoRepository.buscarContrato(contratoId);
        hotelReservaConsumer.destroyAHotelReserva(buscarContrato.getHotelReservaId());
        passagemReservaConsumer.destroyAPassagemReserva(buscarContrato.getPassagemReservaId());

        Agencia agencia = agenciaRepository.buscarAgencia(agenciaId);
        agencia.removeContrato(contratoId);
        agenciaRepository.atualizarAgencia(agencia);

        return Response.ok().build();
    }

    @GET
    @Path("{contratoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContrato(
            @DefaultValue("-1") @PathParam("contratoId") int contratoId,
            @Context UriInfo uriInfo) throws Exception {
        Contrato contrato = contratoRepository.buscarContrato(contratoId);
        return Response.ok(ContratoSkinny.valueOf(contrato, uriInfo)).build();
    }

    private Contrato fillContrato(ContratoResource contratoResource) throws Exception, NumberFormatException {
        Contrato contrato = converter.convert(contratoResource);
        
        HotelReservaRequestEntity hotelEntityRequest
                = fillHERequest(contratoResource, contrato.getPacote());
        
        PassagemReservaResquestEntity passagemEntityRequest
                = fillPERequest(contratoResource, contrato.getPacote());       
        
        int hotelReservaId = Integer.parseInt(hotelReservaConsumer
                .makeAHotelReserva(hotelEntityRequest));
        
        int passagemReservaId = Integer.parseInt(passagemReservaConsumer
                .makeAPassagemReserva(passagemEntityRequest));

        contrato.setHotelReservaId(hotelReservaId);
        contrato.setPassagemReservaId(passagemReservaId);
        return contrato;
    }

    private PassagemReservaResquestEntity fillPERequest(ContratoResource contratoResource, Pacote pacote) {
        PassagemReservaResquestEntity passagemEntityRequest
                = new PassagemReservaResquestEntity();
        passagemEntityRequest.setClienteCpf(contratoResource.getClienteCpf());
        passagemEntityRequest.setPassagem_id(pacote.getPassagemId());
        passagemEntityRequest.setVoo_id(pacote.getVooId());
        return passagemEntityRequest;
    }

    private HotelReservaRequestEntity fillHERequest(ContratoResource contratoResource, Pacote pacote) {
        HotelReservaRequestEntity hotelEntityRequest = new HotelReservaRequestEntity();
        hotelEntityRequest.setClienteCpf(contratoResource.getClienteCpf());
        hotelEntityRequest.setHotel_id(pacote.getHotelId());
        hotelEntityRequest.setQuarto_id(pacote.getQuartoId());
        hotelEntityRequest.setInicio(format.format(pacote.getInicio()));
        hotelEntityRequest.setTermino(format.format(pacote.getTermino()));
        return hotelEntityRequest;
    }
}
