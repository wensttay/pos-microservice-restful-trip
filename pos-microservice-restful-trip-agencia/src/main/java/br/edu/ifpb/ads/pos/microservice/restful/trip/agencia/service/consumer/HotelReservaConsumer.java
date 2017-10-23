package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.mapper.Mapper;
import static br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.HotelConsumer.HOTEL_REST_RESOURCE;
import static br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.HotelConsumer.REAL_HOTEL_HOST;
import static br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.HotelConsumer.REAL_HOTEL_PORT;
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
 * @date 23/10/2017, 13:30:04
 */
@RequestScoped
public class HotelReservaConsumer {

    public static final String HOTEL_HOST = "restful-trip-hotel-api";
    public static final String HOTEL_PORT = "8080";
    public static final String HOTEL_REST_RESOURCE
            = "pos-microservice-restful-trip-hotel/api/reservas";
    public static final String REAL_HOTEL_HOST = "localhost";
    public static final String REAL_HOTEL_PORT = "8082";

    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target("http://" + HOTEL_HOST
            + ":" + HOTEL_PORT + "/" + HOTEL_REST_RESOURCE);

    @Inject
    private Mapper mapper;

    public String getUri(int reservaId, UriInfo uriInfo) {

        return "http://" + REAL_HOTEL_HOST
                + ":" + REAL_HOTEL_PORT
                + "/" + HOTEL_REST_RESOURCE
                + "/" + reservaId;
    }

    public String makeAHotelReserva(HotelReservaRequestEntity requestEntity) {

        String requestEntityJson = mapper.toString(requestEntity);
        
        Response postResponse = target
                .request()
                .post(Entity.json(requestEntityJson));

        return getIdFromUri(postResponse.getHeaderString("Location"));
    }
    
    public String makeAHotelReserva(String clientCpf, int hotel_id, String quarto_id,
            String inicio, String termino) {

        HotelReservaRequestEntity requestEntity = new HotelReservaRequestEntity();
        requestEntity.setClienteCpf(clientCpf);
        requestEntity.setHotel_id(hotel_id);
        requestEntity.setQuarto_id(hotel_id);
        requestEntity.setInicio(inicio);
        requestEntity.setTermino(termino);

        String requestEntityJson = mapper.toString(requestEntity);

        Response postResponse = target
                .request()
                .post(Entity.json(requestEntityJson));

        return getIdFromUri(postResponse.getHeaderString("Location"));
    }

   
    public void destroyAHotelReserva(int hotelReservaId) {
        WebTarget delTarget = client.target("http://" + HOTEL_HOST
            + ":" + HOTEL_PORT + "/" + HOTEL_REST_RESOURCE + "/" + hotelReservaId);
        delTarget.request().delete();
    }
    
    public String getIdFromUri(String uri) {
        int lastIndexOf = uri.lastIndexOf("/");
        return uri.substring(lastIndexOf + 1, uri.length());
    }
}
