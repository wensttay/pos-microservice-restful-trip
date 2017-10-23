package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.skinny;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Contrato;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.ClienteConsumer;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.HotelReservaConsumer;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.PassagemReservaConsumer;
import java.io.Serializable;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 07:25:04
 */
public class ContratoSkinny implements Serializable {

    private int id;
    private SkinnyRef cliente;
    private PacoteSkinny pacote;
    private SkinnyRef hotelReserva;
    private SkinnyRef passagemReserva;

    public ContratoSkinny() {
        cliente = new SkinnyRef();
        pacote = new PacoteSkinny();
        hotelReserva = new SkinnyRef();
        passagemReserva = new SkinnyRef();
    }

    public ContratoSkinny(int id, SkinnyRef cliente, PacoteSkinny pacote,
            SkinnyRef hotelReserva, SkinnyRef passagemReserva) {
        
        this.id = id;
        this.cliente = cliente;
        this.pacote = pacote;
        this.hotelReserva = hotelReserva;
        this.passagemReserva = passagemReserva;
    }

    public static ContratoSkinny valueOf(Contrato contrato, UriInfo uriInfo) throws Exception {
        SkinnyRef clienteSkinnyRef = new SkinnyRef(contrato.getClienteCpf(),
                new ClienteConsumer().getUri(contrato.getClienteCpf(), uriInfo));

        PacoteSkinny pacoteSkinny = PacoteSkinny
                .valueOf(contrato.getPacote(), uriInfo);
        
        SkinnyRef hotelSkinnyRef
                = new SkinnyRef(String.valueOf(contrato.getHotelReservaId()),
                        new HotelReservaConsumer()
                                .getUri(contrato.getHotelReservaId(), uriInfo));
        
        SkinnyRef passagemSkinnyRef
                = new SkinnyRef(String.valueOf(contrato.getPassagemReservaId()),
                        new PassagemReservaConsumer()
                                .getUri(contrato.getPassagemReservaId(), uriInfo));
        
        return new ContratoSkinny(contrato.getId(),
                clienteSkinnyRef,
                pacoteSkinny,
                hotelSkinnyRef,
                passagemSkinnyRef);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SkinnyRef getCliente() {
        return cliente;
    }

    public void setCliente(SkinnyRef cliente) {
        this.cliente = cliente;
    }

    public PacoteSkinny getPacote() {
        return pacote;
    }

    public void setPacote(PacoteSkinny pacote) {
        this.pacote = pacote;
    }

    public SkinnyRef getHotelReserva() {
        return hotelReserva;
    }

    public void setHotelReserva(SkinnyRef hotelReserva) {
        this.hotelReserva = hotelReserva;
    }

    public SkinnyRef getPassagemReserva() {
        return passagemReserva;
    }

    public void setPassagemReserva(SkinnyRef passagemReserva) {
        this.passagemReserva = passagemReserva;
    }

    @Override
    public String toString() {
        return "ContratoSkinny{" + "id=" + id + ", cliente=" + cliente + ", pacote=" + pacote + ", hotelReserva=" + hotelReserva + ", passagemReserva=" + passagemReserva + '}';
    }

}
