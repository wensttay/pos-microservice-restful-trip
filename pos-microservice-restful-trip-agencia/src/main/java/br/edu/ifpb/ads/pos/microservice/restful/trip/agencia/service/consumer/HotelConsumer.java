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
 * @date 23/10/2017, 08:03:55
 */
@RequestScoped
public class HotelConsumer {

    public static final String HOTEL_HOST = "restful-trip-hotel-api";
    public static final String HOTEL_PORT = "8080";
    public static final String HOTEL_REST_RESOURCE
            = "pos-microservice-restful-trip-hotel/api/hoteis";
    public static final String REAL_HOTEL_HOST = "localhost";
    public static final String REAL_HOTEL_PORT = "8082";

    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target("http://" + HOTEL_HOST
            + ":" + HOTEL_PORT + "/" + HOTEL_REST_RESOURCE);

    public String getUri(int hotelId, UriInfo uriInfo) {

        return "http://" + REAL_HOTEL_HOST
                + ":" + REAL_HOTEL_PORT
                + "/" + HOTEL_REST_RESOURCE
                + "/" + hotelId;
    }

    public boolean exists(int hotelId) {

        Response getResponse = this.target
                .path(String.valueOf(hotelId))
                .request()
                .get();

        return getResponse.getStatus() != 404;
    }
}
