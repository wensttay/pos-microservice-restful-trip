package br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.skinny;

import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.model.Agencia;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.AgenciaService;
import br.edu.ifpb.ads.pos.microservice.restful.trip.agencia.service.PacoteService;
import java.io.Serializable;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 23/10/2017, 07:25:13
 */
public class AgenciaSkinny implements Serializable {

    private int id;
    private String nome;
    private String endereco;
    private String CNPJ;
    private String pacotes_uri;
    private String contratos_uri;

    public AgenciaSkinny() {
    }

    public AgenciaSkinny(int id, String nome, String endereco, String CNPJ, String pacotes_uri, String contratos_uri) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.CNPJ = CNPJ;
        this.pacotes_uri = pacotes_uri;
        this.contratos_uri = contratos_uri;
    }

    public static AgenciaSkinny valueOf(Agencia agencia, UriInfo uriInfo) {
        String pacotoUri = AgenciaService
                .getUri(agencia.getId(), uriInfo) + "/pacotes";
        String contratosUri = AgenciaService
                .getUri(agencia.getId(), uriInfo) + "/contratos";

        return new AgenciaSkinny(agencia.getId(),
                agencia.getNome(),
                agencia.getEndereco(),
                agencia.getCNPJ(),
                pacotoUri,
                contratosUri);
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

    public String getPacotes_uri() {
        return pacotes_uri;
    }

    public void setPacotes_uri(String pacotes_uri) {
        this.pacotes_uri = pacotes_uri;
    }

    public String getContratos_uri() {
        return contratos_uri;
    }

    public void setContratos_uri(String contratos_uri) {
        this.contratos_uri = contratos_uri;
    }

    @Override
    public String toString() {
        return "AgenciaSkinny{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", CNPJ=" + CNPJ + ", pacotes_uri=" + pacotes_uri + ", contratos_uri=" + contratos_uri + '}';
    }

}
