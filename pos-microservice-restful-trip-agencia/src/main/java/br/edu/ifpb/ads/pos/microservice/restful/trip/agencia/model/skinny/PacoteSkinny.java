package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.skinny;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.PassagemConsumer;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.QuartoConsumer;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.HotelConsumer;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Pacote;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.VooConsumer;
import java.io.Serializable;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 07:24:51
 */
public class PacoteSkinny implements Serializable {

    private int id;
    private String nome;
    private Double precoTotal;
    private SkinnyRef hotel;
    private SkinnyRef quarto;
    private SkinnyRef voo;
    private SkinnyRef passagem;
    

    public PacoteSkinny() {
        hotel = new SkinnyRef();
        quarto = new SkinnyRef();
        voo = new SkinnyRef();
        passagem = new SkinnyRef();
    }

    public PacoteSkinny(int id, String nome, Double precoTotal, SkinnyRef hotel, SkinnyRef quarto, SkinnyRef voo, SkinnyRef passagem) {
        this.id = id;
        this.nome = nome;
        this.precoTotal = precoTotal;
        this.hotel = hotel;
        this.quarto = quarto;
        this.voo = voo;
        this.passagem = passagem;
    }

    public static PacoteSkinny valueOf(Pacote pacote, UriInfo uriInfo) throws Exception {
        
        HotelConsumer hotelConsumer = new HotelConsumer();
        QuartoConsumer quartoConsumer = new QuartoConsumer();
        VooConsumer vooConsumer = new VooConsumer();
        PassagemConsumer passagemConsumer = new PassagemConsumer();

        if (!hotelConsumer.exists(pacote.getHotelId())
                || !quartoConsumer.exists(pacote.getQuartoId())
                || !vooConsumer.exists(pacote.getVooId())
                || !passagemConsumer.exists(pacote.getPassagemId())) {
            throw new Exception();
        }

        SkinnyRef hotel = new SkinnyRef(String.valueOf(pacote.getHotelId()),
                hotelConsumer.getUri(pacote.getHotelId(), uriInfo));

        SkinnyRef quarto = new SkinnyRef(String.valueOf(pacote.getQuartoId()),
                quartoConsumer.getUri(pacote.getQuartoId(), uriInfo));

        SkinnyRef voo = new SkinnyRef(String.valueOf(pacote.getVooId()),
                vooConsumer.getUri(pacote.getVooId(), uriInfo));
        
        SkinnyRef passagem = new SkinnyRef(String.valueOf(pacote.getPassagemId()),
                passagemConsumer.getUri(pacote.getPassagemId(), uriInfo));

        return new PacoteSkinny(pacote.getId(), pacote.getNome(),
                pacote.getPrecoTotal(), hotel, quarto, voo, passagem);

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

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public SkinnyRef getHotel() {
        return hotel;
    }

    public void setHotel(SkinnyRef hotel) {
        this.hotel = hotel;
    }

    public SkinnyRef getQuarto() {
        return quarto;
    }

    public void setQuarto(SkinnyRef quarto) {
        this.quarto = quarto;
    }

    public SkinnyRef getVoo() {
        return voo;
    }

    public void setVoo(SkinnyRef voo) {
        this.voo = voo;
    }

    public SkinnyRef getPassagem() {
        return passagem;
    }

    public void setPassagem(SkinnyRef passagem) {
        this.passagem = passagem;
    }

    @Override
    public String toString() {
        return "PacoteSkinny{" + "id=" + id + ", nome=" + nome + ", precoTotal=" + precoTotal + ", hotel=" + hotel + ", quarto=" + quarto + ", voo=" + voo + ", passagem=" + passagem + '}';
    }


}
