package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.resource;

import java.io.Serializable;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 04:14:22
 */
public class VooResource implements Serializable {

    private int id;
    private String code;
    private String destino;
    private String compania;
    private String saida;
    private String chegada;

    public VooResource() {
    }

    public VooResource(int id, String code, String destino, String compania, String saida, String chegada) {
        this.id = id;
        this.code = code;
        this.destino = destino;
        this.compania = compania;
        this.saida = saida;
        this.chegada = chegada;
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

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public String getChegada() {
        return chegada;
    }

    public void setChegada(String chegada) {
        this.chegada = chegada;
    }
    
    
}
