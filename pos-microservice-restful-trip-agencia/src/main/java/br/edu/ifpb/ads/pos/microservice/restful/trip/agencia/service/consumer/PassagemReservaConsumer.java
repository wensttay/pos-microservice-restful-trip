package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.mapper.Mapper;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 13:30:39
 */
@RequestScoped
public class PassagemReservaConsumer {

    public static final String PASSAGEM_HOST = "restful-trip-passagem-api";
    public static final String PASSAGEM_PORT = "8080";
    public static final String PASSAGEM_REST_RESOURCE
            = "pos-microservice-restful-trip-passagem/api/reservas";
    public static final String REAL_PASSAGEM_HOST = "localhost";
    public static final String REAL_PASSAGEM_PORT = "8083";

    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target("http://" + PASSAGEM_HOST
            + ":" + PASSAGEM_PORT + "/" + PASSAGEM_REST_RESOURCE);

    @Inject
    private Mapper mapper;

    public String getUri(int reservaId, UriInfo uriInfo) {

        return "http://" + REAL_PASSAGEM_HOST
                + ":" + REAL_PASSAGEM_PORT
                + "/" + PASSAGEM_REST_RESOURCE
                + "/" + reservaId;
    }

    public String makeAPassagemReserva(PassagemReservaResquestEntity requestEntity) {

        String requestEntityJson = mapper.toString(requestEntity);

        Response postResponse = target
                .request()
                .post(Entity.json(requestEntityJson));

        return getIdFromUri(postResponse.getHeaderString("Location"));
    }
    
    public String makeAPassagemReserva(String clientCpf, int voo_id, int passagem_id) {

        PassagemReservaResquestEntity requestEntity = new PassagemReservaResquestEntity();
        requestEntity.setClienteCpf(clientCpf);
        requestEntity.setVoo_id(voo_id);
        requestEntity.setPassagem_id(passagem_id);

        String requestEntityJson = mapper.toString(requestEntity);

        Response postResponse = target
                .request()
                .post(Entity.json(requestEntityJson));

        return getIdFromUri(postResponse.getHeaderString("Location"));
    }

    public String getIdFromUri(String uri) {
        int lastIndexOf = uri.lastIndexOf("/");
        return uri.substring(lastIndexOf + 1, uri.length());
    }

    public void destroyAPassagemReserva(int passagemReservaId) {
        WebTarget delTarget = client.target("http://" + PASSAGEM_HOST
            + ":" + PASSAGEM_PORT + "/" + PASSAGEM_REST_RESOURCE + "/" 
                + passagemReservaId);
        delTarget.request().delete();
    }
    
   
}
