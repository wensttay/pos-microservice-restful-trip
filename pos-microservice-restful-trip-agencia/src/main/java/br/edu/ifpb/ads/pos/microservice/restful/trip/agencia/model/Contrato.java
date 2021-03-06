package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 17/10/2017, 11:10:16
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Contrato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String clienteCpf;
    @Column(nullable = false)
    private int hotelReservaId;
    @Column(nullable = false)
    private int passagemReservaId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "pacote_id")
    private Pacote pacote;

    public Contrato() {
        pacote = new Pacote();
    }

    public Contrato(String clienteCpf, Pacote pacote) {
        this.clienteCpf = clienteCpf;
        this.pacote = pacote;
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

    public int getHotelReservaId() {
        return hotelReservaId;
    }

    public void setHotelReservaId(int hotelReservaId) {
        this.hotelReservaId = hotelReservaId;
    }

    public int getPassagemReservaId() {
        return passagemReservaId;
    }

    public void setPassagemReservaId(int passagemReservaId) {
        this.passagemReservaId = passagemReservaId;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    @Override
    public String toString() {
        return "Contrato{" + "id=" + id + ", clienteCpf=" + clienteCpf + ", hotelReservaId=" + hotelReservaId + ", passagemReservaId=" + passagemReservaId + ", pacote=" + pacote + '}';
    }

}
