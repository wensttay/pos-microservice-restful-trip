package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 17/10/2017, 02:42:20
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Pacote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(unique = true, nullable = false)
    private String nome;
    @Column(precision = 11, scale = 2)
    private Double precoTotal;
    @Column(nullable = false)
    private int hotelId;
    @Column(nullable = false)
    private int quartoId;
    @Column(nullable = false)
    private int vooId;
    @Column(nullable = false)
    private int passagemId;
    private Date inicio;
    private Date termino;

    public Pacote() {
        inicio = new Date();
        termino = new Date();
    }

    public Pacote(int id, String nome, Double precoTotal, int hotelId, int quartoId, int vooId, int passagemId, Date inicio, Date termino) {
        this.id = id;
        this.nome = nome;
        this.precoTotal = precoTotal;
        this.hotelId = hotelId;
        this.quartoId = quartoId;
        this.vooId = vooId;
        this.passagemId = passagemId;
        this.inicio = inicio;
        this.termino = termino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getQuartoId() {
        return quartoId;
    }

    public void setQuartoId(int quartoId) {
        this.quartoId = quartoId;
    }

    public int getVooId() {
        return vooId;
    }

    public void setVooId(int vooId) {
        this.vooId = vooId;
    }

    public int getPassagemId() {
        return passagemId;
    }

    public void setPassagemId(int passagemId) {
        this.passagemId = passagemId;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    @Override
    public String toString() {
        return "Pacote{" + "id=" + id + ", nome=" + nome + ", precoTotal=" + precoTotal + ", hotelId=" + hotelId + ", quartoId=" + quartoId + ", vooId=" + vooId + ", passagemId=" + passagemId + ", inicio=" + inicio + ", termino=" + termino + '}';
    }

}
