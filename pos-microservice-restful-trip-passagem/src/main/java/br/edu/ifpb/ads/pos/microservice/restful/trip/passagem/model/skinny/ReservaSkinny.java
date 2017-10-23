package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.skinny;

import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Reserva;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.service.PassagemService;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.service.VooService;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.service.consumer.ClienteConsumer;
import java.io.Serializable;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 03:46:21
 */
public class ReservaSkinny implements Serializable {

    private int id;
    private SkinnyRef voo;
    private SkinnyRef passagem;
    private SkinnyRef clienteCpf;

    public ReservaSkinny() {
    }

    public ReservaSkinny(int id, SkinnyRef voo, SkinnyRef passagem, SkinnyRef clienteCpf) {
        this.id = id;
        this.voo = voo;
        this.passagem = passagem;
        this.clienteCpf = clienteCpf;
    }

    public static ReservaSkinny valueOf(Reserva reserva, UriInfo uriInfo) {
        
        SkinnyRef vooSkinnyRef = new SkinnyRef();
        vooSkinnyRef.setDescricao(reserva.getVoo().getDestino());
        vooSkinnyRef.setUri(VooService
                .getUri(reserva.getVoo().getId(), uriInfo).toString());

        SkinnyRef passagemSkinnyRef = new SkinnyRef();
        passagemSkinnyRef.setDescricao(reserva.getPassagem().getCode());
        passagemSkinnyRef.setUri(PassagemService
                .getUri(reserva.getPassagem().getId(), uriInfo).toString());

        SkinnyRef clienteSkinnyRef = new SkinnyRef();
        clienteSkinnyRef.setDescricao(reserva.getClienteCpf());
        clienteSkinnyRef.setUri(new ClienteConsumer()
                .getUri(reserva.getClienteCpf(), uriInfo));

        return new ReservaSkinny(reserva.getId(),
                vooSkinnyRef, passagemSkinnyRef, clienteSkinnyRef);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public SkinnyRef getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(SkinnyRef clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    @Override
    public String toString() {
        return "ReservaSkinny{" + "id=" + id + ", voo=" + voo + ", passagem=" + passagem + ", clienteCpf=" + clienteCpf + '}';
    }

}
