package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.skinny;

import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Hotel;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Quarto;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Reserva;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.service.HotelQuartoService;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.service.HotelService;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.service.QuartoService;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 22/10/2017, 17:53:31
 */
public class HotelSkinny implements Serializable {

    private int id;
    private String nome;
    private String endereco;
    private String quartos_uri;

    public HotelSkinny() {
    }

    public HotelSkinny(int id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public static HotelSkinny valueOf(Hotel hotel, UriInfo uriInfo) {

        HotelSkinny hotelSkinny
                = new HotelSkinny(
                        hotel.getId(),
                        hotel.getNome(),
                        hotel.getEndereco());

        hotelSkinny.setQuartos_uri(HotelService
                .getUri(hotel.getId(), uriInfo).toString() + "/quartos");

        return hotelSkinny;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getQuartos_uri() {
        return quartos_uri;
    }

    public void setQuartos_uri(String quartos_uri) {
        this.quartos_uri = quartos_uri;
    }

    @Override
    public String toString() {
        return "HotelSkinny{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", quartos=" + quartos_uri + '}';
    }

}
