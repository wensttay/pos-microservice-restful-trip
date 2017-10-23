package br.edu.ifpb.ads.pos.microservice.restful.trip.hotel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 02/10/2017, 10:55:27
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Hotel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Column(unique = true)
    private String nome;
    private String endereco;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id")
    private List<Quarto> quartos;

    public Hotel() {
        quartos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public List<Quarto> getQuartos() {
        return Collections.unmodifiableList(quartos);
    }

    public boolean addQuarto(Quarto quarto) {
        return this.quartos.add(quarto);
    }
    
    public boolean removerQuarto(int quartoId) {
        for (Quarto quarto : quartos) {
            if(quarto.getId() == quartoId){
                quartos.remove(quarto);
                return true;
            }
        }
        return false;
    }
   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    

    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", quartos=" + quartos + '}';
    }
    
}
