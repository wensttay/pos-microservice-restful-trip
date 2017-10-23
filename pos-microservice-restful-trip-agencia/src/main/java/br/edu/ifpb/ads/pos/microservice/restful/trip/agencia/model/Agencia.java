package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
 * @date 17/10/2017, 02:46:34
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Agencia implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nome;
    private String endereco;
    private String CNPJ;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "agencia_id")
    private List<Pacote> pacotes;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "agencia_id")
    private List<Contrato> contratos;

    public Agencia() {
        this.pacotes = new ArrayList<>();
        this.contratos = new ArrayList<>();
    }

    public Agencia(String nome, String endereco, String CNPJ) {
        this.nome = nome;
        this.endereco = endereco;
        this.CNPJ = CNPJ;
        this.pacotes = new ArrayList<>();
        this.contratos = new ArrayList<>();
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public List<Pacote> getPacotes() {
        return pacotes;
    }

    public void setPacotes(List<Pacote> pacotes) {
        this.pacotes = pacotes;
    }
    
    public boolean addPacote(Pacote pacote) {
        return this.pacotes.add(pacote);
    }
    
    public boolean removePacote(int pacoteId) {
        for (Pacote pacote : pacotes) {
            if(pacote.getId() == pacoteId){
                this.pacotes.remove(pacote);
                return true;
            }
        }
        return false;
    }
    
    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
    
    public boolean addContrato(Contrato contrato) {
        return this.contratos.add(contrato);
    }
    
    public boolean removeContrato(int contratoId) {
        for (Contrato contrato : contratos) {
            if(contrato.getId() == contratoId){
                this.contratos.remove(contrato);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Agencia{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", CNPJ=" + CNPJ + ", pacotes=" + pacotes + '}';
    }
    
}
