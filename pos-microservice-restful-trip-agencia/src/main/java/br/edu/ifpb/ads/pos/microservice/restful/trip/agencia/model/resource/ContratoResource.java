package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.resource;

import java.io.Serializable;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 07:24:05
 */
public class ContratoResource implements Serializable {

    private int id;
    private String clienteCpf;
    private int pacoteId;

    public ContratoResource() {
    }

    public ContratoResource(int id, String clienteCpf, int pacoteId) {
        this.id = id;
        this.clienteCpf = clienteCpf;
        this.pacoteId = pacoteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public int getPacoteId() {
        return pacoteId;
    }

    public void setPacoteId(int pacoteId) {
        this.pacoteId = pacoteId;
    }

    @Override
    public String toString() {
        return "ContratoResource{" + "id=" + id + ", clienteCpf=" + clienteCpf + ", pacoteId=" + pacoteId + '}';
    }
    
}
