package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model.resource;

import java.io.Serializable;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 22/10/2017, 18:39:14
 */
public class ReservaResource implements Serializable {
    
    private int id;
    private String inicio;
    private String termino;
    private int hotel_id;
    private int quarto_id;
    private String clienteCpf;

    public ReservaResource() {
    }

    public ReservaResource(String inicio, String termino, int hotel_id, int quarto_id, String clienteCpf) {
        this.inicio = inicio;
        this.termino = termino;
        this.hotel_id = hotel_id;
        this.quarto_id = quarto_id;
        this.clienteCpf = clienteCpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    @Override
    public String toString() {
        return "ReservaResource{" + "id=" + id + ", inicio=" + inicio + ", termino=" + termino + ", hotel_id=" + hotel_id + ", quarto_id=" + quarto_id + ", cliente_id=" + clienteCpf + '}';
    }
    
}
