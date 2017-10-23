package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.skinny;

import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Quarto;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.Reserva;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.service.HotelService;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.service.QuartoService;
import br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.service.consumer.ClienteConsumer;
import java.io.Serializable;
import java.util.Date;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 22/10/2017, 17:53:51
 */
public class ReservaSkinny implements Serializable {

    private int id;
    private Date inicio;
    private Date termino;
    private SkinnyRef hotel;
    private SkinnyRef quarto;
    private SkinnyRef cliente;

    public ReservaSkinny() {
        this.hotel = new SkinnyRef();
        this.quarto = new SkinnyRef();
        this.cliente = new SkinnyRef();
    }

    public ReservaSkinny(int id, Date inicio, Date termino) {
        this.hotel = new SkinnyRef();
        this.quarto = new SkinnyRef();
        this.cliente = new SkinnyRef();
        this.id = id;
        this.inicio = inicio;
        this.termino = termino;
    }

    public static ReservaSkinny valueOf(Reserva reserva, UriInfo uriInfo) {
        ReservaSkinny reservaSkinny = new ReservaSkinny(reserva.getId(), 
                reserva.getInicio(), reserva.getTermino());
        
        SkinnyRef clienteRef = new SkinnyRef();
        clienteRef.setDescricao(reserva.getClienteCpf());
        clienteRef.setUri(new ClienteConsumer().getUri(reserva.getClienteCpf(), uriInfo));
        
        SkinnyRef hotelRef = new SkinnyRef();
        hotelRef.setDescricao(reserva.getHotel().getNome());
        hotelRef.setUri(HotelService.getUri(reserva.getHotel().getId(), uriInfo).toString());
        
        SkinnyRef quartoRef = new SkinnyRef();
        quartoRef.setDescricao(reserva.getQuarto().getDescricao());
        quartoRef.setUri(QuartoService.getUri(reserva.getQuarto().getId(), uriInfo).toString());
        
        reservaSkinny.setCliente(clienteRef);
        reservaSkinny.setHotel(hotelRef);
        reservaSkinny.setQuarto(quartoRef);
        
        return reservaSkinny;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
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

    public SkinnyRef getCliente() {
        return cliente;
    }

    public void setCliente(SkinnyRef cliente) {
        this.cliente = cliente;
    }

}
