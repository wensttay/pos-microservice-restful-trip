package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.consumer;

import java.io.Serializable;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 13:40:53
 */
public class HotelReservaRequestEntity implements Serializable {
    
    private String clienteCpf;
    private int hotel_id;
    private int quarto_id;
    private String inicio;
    private String termino;

    public HotelReservaRequestEntity() {
    }

    public HotelReservaRequestEntity(String clienteCpf, int hotel_id, int quarto_id, String inicio, String termino) {
        this.clienteCpf = clienteCpf;
        this.hotel_id = hotel_id;
        this.quarto_id = quarto_id;
        this.inicio = inicio;
        this.termino = termino;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getQuarto_id() {
        return quarto_id;
    }

    public void setQuarto_id(int quarto_id) {
        this.quarto_id = quarto_id;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    @Override
    public String toString() {
        return "HotelReservaRequestEntity{" + "clienteCpf=" + clienteCpf + ", hotel_id=" + hotel_id + ", quarto_id=" + quarto_id + ", inicio=" + inicio + ", termino=" + termino + '}';
    }
    
}
