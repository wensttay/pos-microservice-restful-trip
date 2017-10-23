package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.skinny;

import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Passagem;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.Voo;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.service.PassagemService;
import br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.service.VooService;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 02:21:18
 */
public class VooSkinny implements Serializable {

    private int id;
    private String code;
    private String destino;
    private String compania;
    private Timestamp saida;
    private Timestamp chegada;
    private String passagens_uri;

    public VooSkinny() {
    }

    public VooSkinny(int id, String code, String destino, String compania, Timestamp saida, Timestamp chegada) {
        this.id = id;
        this.code = code;
        this.destino = destino;
        this.compania = compania;
        this.saida = saida;
        this.chegada = chegada;
    }
    
    public static VooSkinny valueOf(Voo voo, UriInfo uriInfo){
        VooSkinny vooSkinny = new VooSkinny(
                voo.getId(), 
                voo.getCode(), 
                voo.getDestino(), 
                voo.getCompania(), 
                voo.getSaida(), 
                voo.getChegada());
        
        vooSkinny.setPassagens_uri(VooService
                .getUri(voo.getId(), uriInfo).toString() + "/passagens");
        
        return vooSkinny;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Timestamp getSaida() {
        return saida;
    }

    public void setSaida(Timestamp saida) {
        this.saida = saida;
    }

    public Timestamp getChegada() {
        return chegada;
    }

    public void setChegada(Timestamp chegada) {
        this.chegada = chegada;
    }

    public String getPassagens_uri() {
        return passagens_uri;
    }

    public void setPassagens_uri(String passagens_uri) {
        this.passagens_uri = passagens_uri;
    }

    @Override
    public String toString() {
        return "VooSkinny{" + "id=" + id + ", code=" + code + ", destino=" + destino + ", compania=" + compania + ", saida=" + saida + ", chegada=" + chegada + ", passagens=" + passagens_uri + '}';
    }
    
}
