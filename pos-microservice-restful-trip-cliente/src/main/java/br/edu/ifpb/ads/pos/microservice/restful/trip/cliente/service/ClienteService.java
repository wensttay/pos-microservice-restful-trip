package br.edu.ifpb.ads.pos.microservice.restful.trip.cliente.service;

import br.edu.ifpb.ads.pos.microservice.restful.trip.cliente.model.Cliente;
import br.edu.ifpb.ads.pos.microservice.restful.trip.cliente.repository.ClienteRepository;
import java.net.URI;
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
 * @date 02/10/2017, 10:34:58
 */
@Path("clientes")
@RequestScoped
public class ClienteService {

    @Inject
    private ClienteRepository repository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvar(Cliente c, @Context UriInfo uriInfo) {
        System.out.println("Salvar um Cliente");
        repository.salvarCliente(c);

        URI uri = uriInfo.getBaseUriBuilder()
                .path(this.getClass())
                .path(String.valueOf(c.getId()))
                .build();

        return Response.created(uri).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Cliente c) {
        System.out.println("Atualizar um Cliente");
        repository.atualizarCliente(c);

        return Response.ok().build();
    }

    @DELETE
    @Path("{clienteCpf}")
    public Response deletar(@DefaultValue("-1") @PathParam("clienteCpf") String clienteCpf) {
        System.out.println("Deletar um Cliente");
        repository.deletarCliente(clienteCpf);

        return Response.ok().build();
    }

    @GET
    @Path("{clienteCpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarByCpf(@DefaultValue("-1") @PathParam("clienteCpf") String clienteCpf) {
        System.out.println("Buscar um Cliente por id");
        Cliente cliente = repository.buscarCliente(clienteCpf);
        
        return Response.ok(cliente).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        System.out.println("Listar Clientes");
        
        Cliente[] listarCliente = repository.listarCliente();
        
        GenericEntity<Cliente[]> entity =
                new GenericEntity<Cliente[]>(listarCliente){};
        
        return Response.ok(entity).build();
    }

}
