package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.skinny;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Contrato;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer.ClienteConsumer;
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

    public ContratoSkinny() {
        cliente = new SkinnyRef();
        pacote = new PacoteSkinny();
    }

    public ContratoSkinny(int id, SkinnyRef cliente, PacoteSkinny pacote) {
        this.id = id;
        this.cliente = cliente;
        this.pacote = pacote;
    }

    public static ContratoSkinny valueOf(Contrato contrato, UriInfo uriInfo) throws Exception {
        SkinnyRef clienteSkinnyRef = new SkinnyRef(contrato.getClienteCpf(),
                new ClienteConsumer().getUri(contrato.getClienteCpf(), uriInfo));

        PacoteSkinny pacoteSkinny = PacoteSkinny
                .valueOf(contrato.getPacote(), uriInfo);

        return new ContratoSkinny(contrato.getId(), clienteSkinnyRef, pacoteSkinny);
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

    @Override
    public String toString() {
        return "ContratoSkinny{" + "id=" + id + ", cliente=" + cliente + ", pacote=" + pacote + '}';
    }

}
