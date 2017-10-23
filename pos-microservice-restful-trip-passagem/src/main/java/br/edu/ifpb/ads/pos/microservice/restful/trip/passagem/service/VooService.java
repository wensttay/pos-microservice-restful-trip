package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Voo;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.resource.VooResource;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.resource.VooResourceConverter;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.skinny.VooSkinny;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.repository.VooRepository;
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
 * @date 23/10/2017, 02:15:27
 */
@Path("voos")
@RequestScoped
public class VooService {

    @Inject
    private VooRepository vooRepository;
    @Inject
    private VooPassagemService vooPassagemService;
    @Inject
    private VooResourceConverter converter;

    public static URI getUri(int vooId, UriInfo uriInfo) {

        return uriInfo.getBaseUriBuilder()
                .path(VooService.class)
                .path(String.valueOf(vooId))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvar(VooResource voo, @Context UriInfo uriInfo) {
        Voo convert = converter.convert(voo);
        this.vooRepository.salvarVoo(convert);
        URI createdUri = getUri(convert.getId(), uriInfo);
        
        return Response.created(createdUri).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(VooResource voo) {
        Voo v = converter.convert(voo);
        v.setId(voo.getId());
        v.setPassagems(vooRepository.buscarVooPassagens(voo.getId()));
        
        this.vooRepository.atualizarVoo(v);
        return Response.ok().build();
    }

    @DELETE
    @Path("{vooId}")
    public Response deletar(@DefaultValue("-1") @PathParam("vooId") int vooId) {
        this.vooRepository.deletarVoo(vooId);
        return Response.ok().build();
    }

    @GET
    @Path("{vooId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarById(@DefaultValue("-1") @PathParam("vooId") int vooId) {
        Voo vooFound = this.vooRepository.buscarVoo(vooId);
        return Response.ok(vooFound).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@Context UriInfo uriInfo) {

        List<VooSkinny> vooSkinnys = new ArrayList<>();
        List<Voo> voos = vooRepository.listarVoos();
        
        for (Voo v : voos) {
            vooSkinnys.add(VooSkinny.valueOf(v, uriInfo));
        }

        GenericEntity<List<VooSkinny>> entity
                = new GenericEntity<List<VooSkinny>>(vooSkinnys) {
        };

        return Response.ok(entity).build();
    }

    @Path("{vooId}/passagens")
    public VooPassagemService vooPassagens() {
        return vooPassagemService;
    }
}
