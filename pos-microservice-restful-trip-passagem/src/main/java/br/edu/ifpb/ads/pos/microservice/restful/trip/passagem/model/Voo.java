package br.edu.ifpb.ads.pos.microservice.restful.trip.passagem.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 17/10/2017, 02:41:39
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Voo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String destino;
    @Column(nullable = false)
    private String compania;
    private Timestamp saida;
    private Timestamp chegada;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "voo_id")
    private List<Passagem> passagems;

    public Voo() {
        passagems = new ArrayList<>();
    }

    public Voo(String code, String destino, String compania) {
        this.code = code;
        this.destino = destino;
        this.compania = compania;
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

    public List<Passagem> getPassagems() {
        return passagems;
    }

    public void setPassagems(List<Passagem> passagems) {
        this.passagems = passagems;
    }

    public boolean addPassagem(Passagem passagem) {
        return this.passagems.add(passagem);
    }

    public boolean removerPassagem(int passagemId) {
        for (Passagem passagem : passagems) {
            if (passagem.getId() == passagemId) {
                passagems.remove(passagem);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Voo{" + "id=" + id + ", code=" + code + ", destino=" + destino + ", compania=" + compania + ", saida=" + saida + ", chegada=" + chegada + ", passagems=" + passagems + '}';
    }
}
