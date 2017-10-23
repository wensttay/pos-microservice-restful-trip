package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 11:18:41
 */
public class VooConsumer {

    public static final String PASSAGEM_HOST = "restful-trip-passagem-api";
    public static final String PASSAGEM_PORT = "8080";
    public static final String PASSAGEM_REST_RESOURCE
            = "pos-microservice-restful-trip-passagem/api/voos";
    public static final String REAL_PASSAGEM_HOST = "localhost";
    public static final String REAL_PASSAGEM_PORT = "8083";

    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target("http://" + PASSAGEM_HOST
            + ":" + PASSAGEM_PORT + "/" + PASSAGEM_REST_RESOURCE);

    public String getUri(int vooId, UriInfo uriInfo) {

        return "http://" + REAL_PASSAGEM_HOST
                + ":" + REAL_PASSAGEM_PORT
                + "/" + PASSAGEM_REST_RESOURCE
                + "/" + vooId;
    }

    public boolean exists(int vooId) {

        Response getResponse = this.target
                .path(String.valueOf(vooId))
                .request()
                .get();

        return getResponse.getStatus() != 404;
    }
}
