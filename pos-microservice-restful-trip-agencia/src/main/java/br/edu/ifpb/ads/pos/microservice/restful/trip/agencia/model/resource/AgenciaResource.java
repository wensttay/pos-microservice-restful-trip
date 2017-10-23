package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.resource;

import java.io.Serializable;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 07:53:00
 */
public class AgenciaResource implements Serializable {

    private int id;
    private String nome;
    private String endereco;
    private String CNPJ;

    public AgenciaResource() {
    }

    public AgenciaResource(int id, String nome, String endereco, String CNPJ) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.CNPJ = CNPJ;
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
    
    
}
