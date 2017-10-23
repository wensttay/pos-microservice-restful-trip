package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Contrato;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.resource.ContratoResource;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.resource.ContratoResourceConverter;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.skinny.ContratoSkinny;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.repository.ContratoRepository;
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
 * @date 23/10/2017, 08:24:37
 */
@Path("contratos")
@RequestScoped
public class ContratoService {

    @Inject
    private ContratoRepository contratoRepository;
    @Inject
    private ContratoResourceConverter contratoResourceConverter;

    public static URI getUri(int contratoId, UriInfo uriInfo) {

        return uriInfo.getBaseUriBuilder()
                .path(ContratoService.class)
                .path(String.valueOf(contratoId))
                .build();
    }

    @GET
    @Path("{contratoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarById(
            @DefaultValue("-1") @PathParam("contratoId") int contratoId,
            @Context UriInfo uriInfo) throws Exception {
        Contrato contrato = this.contratoRepository.buscarContrato(contratoId);
        ContratoSkinny contratoSkinny = ContratoSkinny.valueOf(contrato, uriInfo);
        return Response.ok(contratoSkinny).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@Context UriInfo uriInfo) throws Exception {

        List<ContratoSkinny> contratoSkinnys = new ArrayList<>();
        List<Contrato> contratos = this.contratoRepository.listarContratos();

        for (Contrato contrato : contratos) {
            contratoSkinnys.add(ContratoSkinny.valueOf(contrato, uriInfo));
        }

        GenericEntity<List<ContratoSkinny>> entity = 
                new GenericEntity<List<ContratoSkinny>>(contratoSkinnys) {
        };

        return Response.ok(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(ContratoResource contratoResource) throws Exception {

        Contrato contrato = contratoResourceConverter.convert(contratoResource);
        contrato.setId(contratoResource.getId());
        contratoRepository.atualizarContrato(contrato);

        return Response.ok().build();
    }
}
