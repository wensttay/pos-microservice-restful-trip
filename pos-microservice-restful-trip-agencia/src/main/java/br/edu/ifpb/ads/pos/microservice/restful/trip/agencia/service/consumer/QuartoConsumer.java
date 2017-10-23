package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 08:04:11
 */
@RequestScoped
public class QuartoConsumer {

    public static final String QUARTO_HOST = "restful-trip-hotel-api";
    public static final String QUARTO_PORT = "8080";
    public static final String QUARTO_REST_RESOURCE
            = "pos-microservice-restful-trip-hotel/api/quartos";
    public static final String REAL_QUARTO_HOST = "localhost";
    public static final String REAL_QUARTO_PORT = "8082";

    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target("http://" + QUARTO_HOST
            + ":" + QUARTO_PORT + "/" + QUARTO_REST_RESOURCE);

    public String getUri(int quartoId, UriInfo uriInfo) {

        return "http://" + REAL_QUARTO_HOST
                + ":" + REAL_QUARTO_PORT
                + "/" + QUARTO_REST_RESOURCE
                + "/" + quartoId;
    }

    public boolean exists(int quartoId) {

        Response getResponse = this.target
                .path(String.valueOf(quartoId))
                .request()
                .get();

        return getResponse.getStatus() != 404;
    }

}
