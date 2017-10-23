package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer;

import java.io.Serializable;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 14:05:08
 */
public class PassagemReservaResquestEntity implements Serializable {

    private String clienteCpf;
    private int voo_id;
    private int passagem_id;

    public PassagemReservaResquestEntity() {
    }

    public PassagemReservaResquestEntity(String clienteCpf, int voo_id, int passagem_id) {
        this.clienteCpf = clienteCpf;
        this.voo_id = voo_id;
        this.passagem_id = passagem_id;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
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

    @Override
    public String toString() {
        return "PassagemReservaResquestEntity{" + "clienteCpf=" + clienteCpf + ", voo_id=" + voo_id + ", passagem_id=" + passagem_id + '}';
    }
    
}
