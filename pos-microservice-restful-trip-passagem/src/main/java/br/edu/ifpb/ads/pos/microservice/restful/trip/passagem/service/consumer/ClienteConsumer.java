package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.service.consumer;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 22/10/2017, 19:21:14
 */
@RequestScoped
public class ClienteConsumer {
    
    public static final String CLIENTE_HOST = "restful-trip-cliente-api";
    public static final String CLIENTE_PORT = "8080";
    public static final String CLIENTE_REST_RESOURCE = 
            "pos-microservice-restful-trip-cliente/api/clientes";
    public static final String REAL_CLIENTE_HOST = "localhost";
    public static final String REAL_CLIENTE_PORT = "8081";
    
    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target("http://" + CLIENTE_HOST
            + ":" + CLIENTE_PORT + "/" + CLIENTE_REST_RESOURCE);

    public String getUri(String clienteCpf, UriInfo uriInfo) {

        return "http://" + REAL_CLIENTE_HOST +
                ":" + REAL_CLIENTE_PORT + 
                "/" + CLIENTE_REST_RESOURCE + 
                "/" + clienteCpf;
    }
    
    public boolean exists(String clientCpf) {

        Response getResponse = this.target
                .path(clientCpf)
                .request()
                .get();

        return getResponse.getStatus() != 404;
    }

    public Client getClient() {
        return client;
    }

    public WebTarget getTarget() {
        return target;
    }
    
}
