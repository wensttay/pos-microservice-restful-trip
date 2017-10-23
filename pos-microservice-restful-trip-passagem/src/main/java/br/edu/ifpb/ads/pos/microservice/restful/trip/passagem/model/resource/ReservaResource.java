package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model.resource;

import java.io.Serializable;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 02:29:12
 */
public class ReservaResource implements Serializable {
    
    private int id;
    private int voo_id;
    private int passagem_id;
    private String clienteCpf;

    public ReservaResource() {
    }

    public ReservaResource(int id, int voo_id, int passagem_id, String clienteCpf) {
        this.id = id;
        this.voo_id = voo_id;
        this.passagem_id = passagem_id;
        this.clienteCpf = clienteCpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoo_id() {
        return voo_id;
    }

    public void setVoo_id(int voo_id) {
        this.voo_id = voo_id;
    }

    public int getPassagem_id() {
        return passagem_id;
    }

    public void setPassagem_id(int passagem_id) {
        this.passagem_id = passagem_id;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    @Override
    public String toString() {
        return "ReservaResource{" + "id=" + id + ", voo_id=" + voo_id + ", passagem_id=" + passagem_id + ", clienteCpf=" + clienteCpf + '}';
    }
    
}
